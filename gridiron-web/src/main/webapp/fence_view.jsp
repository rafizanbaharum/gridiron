<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.min.css">
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>

    <style>
        #map-canvas img {
            max-width: none;
        }
    </style>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false&libraries=visualization">
    </script>

    <script type="text/javascript">
        var turfId = ${turf.getId()};
        var districtId = ${district.getId()};
        var map;
        var heatmap;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var data = new google.maps.MVCArray();
        google.load("visualization", "1", {packages:["corechart"]});

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

            addDistrict();
            addNodes();
            addTurf();
        }

        function addTurf() {
            $.getJSON('/turf/findTurf?id=' + turfId, function(turf) {
                var polyOptions = {
                    strokeColor: '#0000FF',
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: '#0000FF',
                    fillOpacity: 0.08,
                    indexID:turf.id,
                    map:map
                };
                var poly = new google.maps.Polygon(polyOptions);
                var bounds = turf.bounds;
                for (var j = 0; j < bounds.length; j++) {
                    var latlng = new google.maps.LatLng(bounds[j].x, bounds[j].y);
                    poly.getPath().push(latlng);
                }
                var center = new google.maps.LatLng(turf.center.x, turf.center.y);
                map.panTo(center);
                attachInfoWindow(poly);
            });
        }

        function addDistrict() {
            $.getJSON('/district/findDistrict?id=' + districtId, function(district) {
                var polyOptions = {
                    strokeColor: '#0000FF',
                    strokeOpacity: 0.5,
                    strokeWeight: 2,
                    fillColor: '#0000FF',
                    fillOpacity: 0.05,
                    indexID:district.id,
                    map:map
                };
                var poly = new google.maps.Polygon(polyOptions);
                var bounds = district.bounds;
                for (var j = 0; j < bounds.length; j++) {
                    var latlng = new google.maps.LatLng(bounds[j].x, bounds[j].y);
                    poly.getPath().push(latlng);
                }
                addTurfs(district.id);
                var center = new google.maps.LatLng(district.center.x, district.center.y);
                map.panTo(center);
            });
        }

        function addNodes() {
            heatmap = new google.maps.visualization.HeatmapLayer({
                map: map,
                data: data,
                radius: 20,
                dissipate: true,
                maxIntensity: 10,
                gradient: [
                    'rgba(0, 255, 255, 0)',
                    'rgba(0, 255, 255, 1)',
                    'rgba(0, 191, 255, 1)',
                    'rgba(0, 127, 255, 1)',
                    'rgba(0, 63, 255, 1)',
                    'rgba(0, 0, 255, 1)',
                    'rgba(0, 0, 223, 1)',
                    'rgba(0, 0, 191, 1)',
                    'rgba(0, 0, 159, 1)',
                    'rgba(0, 0, 127, 1)',
                    'rgba(63, 0, 91, 1)',
                    'rgba(127, 0, 63, 1)',
                    'rgba(191, 0, 31, 1)',
                    'rgba(255, 0, 0, 1)'
                ]
            });

            $.getJSON('/node/findNodesWithinTurf?turfId=' + turfId, function(nodes) {
                for (var i = 0; i < nodes.length; i++) {
                    var node = nodes[i];
                    console.log(node);
                    latlng = new google.maps.LatLng(node.x, node.y);
                    data.push(latlng);
                }
            });
        }

        function attachInfoWindow(polygon) {
            google.maps.event.addListener(polygon, 'click', function(event) {

                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Kecenderungan');
                data.addColumn('number', 'Bilangan');
                data.addRows([
                    ['Biru', 9132],
                    ['Merah', 1121],
                    ['Jingga', 1121],
                    ['Hijau', 113],
                    ['Ungu', 231]
                ]);

                // Set chart options
                var options = {
                    title:'Kecenderungan Kawasan',
                    width:250,
                    height:180
                };
                var inner = document.createElement('div');
                var infoWindow = new google.maps.InfoWindow();
                var chart = new google.visualization.PieChart(inner);

                chart.draw(data, options);
                infoWindow.setContent(inner);
                infoWindow.setPosition(event.latLng);
                infoWindow.open(map);
            });

        }


        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<h3>Turf View: ${turf.name} (${turf.headCount})</h3>

<div id="map-canvas" style="width:100%; height:20em"></div>
<div id="data" style="width:100%">
    <table class="table table-hover" id="sample-table-1">
        <thead>
        <tr>
            <th class="center">#</th>
            <th>Name</th>
            <th>IC</th>
            <th>Phone</th>
            <th>Inclination</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="node" items="${nodes}" varStatus="idx">
            <tr>
                <td>${idx.count}</td>
                <td>${node.name}</td>
                <td>${node.nricNo}</td>
                <td>${node.phone}</td>
                <td>${node.inclinationType}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

