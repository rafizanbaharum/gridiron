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
        var districtId = ${district.id};
        var map;
        var marker;
        var heatmap;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var data = new google.maps.MVCArray();

        google.load("visualization", "1", {packages:["corechart"]});

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 11,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            addDistrict();
            panToCenter()
            addTurfs();

        }

        function addDistrict() {
            $.getJSON('/district/findDistrict?id=' + districtId, function(district) {
                var polyOptions = {
                    strokeColor: '#0000FF',
                    strokeOpacity: 0.5,
                    strokeWeight: 2,
                    fillColor: '#0000FF',
                    fillOpacity: 0.2,
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

        function addTurfs() {
            $.getJSON('/turf/findAllTurfs?districtId=' + districtId, function(turfs) {
                for (var i = 0; i < turfs.length; i++) {
                    var turf = turfs[i];
                    var polyOptions = {
                        strokeColor: '#0000FF',
                        strokeOpacity: 0.5,
                        strokeWeight: 2,
                        fillColor: '#0000FF',
                        fillOpacity: 0.2,
                        indexID:turf.id,
                        map:map
                    };
                    var poly = new google.maps.Polygon(polyOptions);
                    var bounds = turf.bounds;
                    for (var j = 0; j < bounds.length; j++) {
                        var latlng = new google.maps.LatLng(bounds[j].x, bounds[j].y);
                        poly.getPath().push(latlng);
                    }
                    attachInfoWindow(poly);
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
                var main = document.createElement('div');
                var inner = document.createElement('div');
                var link = document.createElement('div');
                var infoWindow = new google.maps.InfoWindow();
                var chart = new google.visualization.PieChart(inner);

                // link to turf
                var turfLink = document.createElement("a")
                turfLink.setAttribute("href", "/turf/view/" + polygon.indexID);
                turfLink.innerHTML = "View Turf";
                link.appendChild(turfLink);

                // link to turf
                var eventLink = document.createElement("a")
                eventLink.setAttribute("href", "/district/view/" + districtId);
                eventLink.innerHTML = " | View Events";
                link.appendChild(eventLink);

                // link to report
                var reportLink = document.createElement("a")
                reportLink.setAttribute("href", "/turf/report/" + polygon.indexID);
                reportLink.innerHTML = " | View Reports";
                link.appendChild(reportLink);

                main.appendChild(inner);
                main.appendChild(link);

                chart.draw(data, options);
                infoWindow.setContent(main);
                infoWindow.setPosition(event.latLng);
                infoWindow.open(map);
            });

        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<h3>Navigate District: ${district.name} (${district.headCount})</h3>

<div id="map-canvas" style="width:100%; height:20em"></div>
<div id="data" style="width:100%">
    <a href="/turf/draw?districtId=${district.id}">Add Turf</a>
    <table class="table table-hover" id="sample-table-1">
        <thead>
        <tr>
            <th class="center">#</th>
            <th>Code</th>
            <th>Name</th>
            <th>Description</th>
            <th>HeadCount</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="turf" items="${turfs}" varStatus="idx">
            <tr>
                <td>${idx.count}</td>
                <td>${turf.code}</td>
                <td>${turf.name}</td>
                <td></td>
                <td>${turf.headCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

