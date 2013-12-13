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
        var map;
        var heatmap;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var data = new google.maps.MVCArray();
        google.load("visualization", "1", {packages:["imagesparkline"]});

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
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
            });
        }

        function addNodes() {
            $.getJSON('/node/findNodessWithinTurf?turfId=' + turfId, function(nodes) {
                for (var i = 0; i < nodes.length; i++) {
                    var node = nodes[i];
                    latlng = new google.maps.LatLng(node.x, node.y);
                    var marker = new google.maps.Marker({
                        position: latlng,
                        map: map,
                        icon: getCircle(node.headCount)
                    });
                }
            });

            function getCircle(count) {
                return {
                    path: google.maps.SymbolPath.CIRCLE,
                    fillColor: '#0000FF',
                    fillOpacity: .2,
                    scale: 1,
                    strokeColor: '#0000FF',
                    strokeWeight: .5
                };
            }
        }

        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<h3>Turf Sentiment and Trend: ${turf.name} (${turf.headCount})</h3>

<div id="map-canvas" style="width:100%; height:20em"></div>
<div id="data" style="width:100%">
    <table class="table table-hover" id="sample-table-1">
        <thead>
        <tr>
            <th>Sentiment</th>
            <th>Trend</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Membership Headcount</td>
            <td>
                <img src="http://chart.apis.google.com/chart?cht=lc&chs=500x30&chd=t:25.3,326.45,15.94,314.7,452.3,321.2,126.5,142.22,247.6,195.2,18.5,26.5,21.2,37.0,52.9,68.2,47.6,68.8,36.5,31.7,42.3&chco=336699&chls=1,1,0&chm=o,990000,0,20,4&chxt=r,x,y&chxs=0,990000,11,0,_|1,990000,1,0,_|2,990000,1,0,_&chxl=0:|8123|1:||2:||&chxp=0,42.3">
            </td>
        </tr>
        <tr>
            <td>Event Attedance</td>
            <td>
                <img src="http://chart.apis.google.com/chart?cht=lc&chs=500x30&chd=t:5.3,26.5,15.9,31.7,42.3,21.2,26.5,42.3,47.6,95.2,18.5,26.5,21.2,37.0,52.9,58.2,47.6,68.8,26.5,31.7,42.3&chco=336699&chls=1,1,0&chm=o,990000,0,20,4&chxt=r,x,y&chxs=0,990000,11,0,_|1,990000,1,0,_|2,990000,1,0,_&chxl=0:|428|1:||2:||&chxp=0,42.3">
            </td>
        </tr>
        <tr>
            <td>Spending</td>
            <td>
                <img src="http://chart.apis.google.com/chart?cht=lc&chs=500x30&chd=t:1521.3,2336.15,1145.9,3251.7,42.362,216.2,226.5,4722.3,47.621,95.2,2118.5,226.5,1321.2,3337.0,522.9,3528.2,47.6,628.8,26.5,31.7,42.3&chco=336699&chls=1,1,0&chm=o,990000,0,20,4&chxt=r,x,y&chxs=0,990000,11,0,_|1,990000,1,0,_|2,990000,1,0,_&chxl=0:|121|1:||2:||&chxp=0,42.3">
            </td>
        </tr>
        <tr>
            <td>Historical GOTV</td>
            <td>
                <img src="http://chart.apis.google.com/chart?cht=lc&chs=500x30&chd=t:51.3,26.15,115.9,321.7,42.32,21.2,226.5,422.3,47.61,95.2,118.5,26.5,121.2,337.0,522.9,3528.2,47.6,628.8,26.5,31.7,42.3&chco=336699&chls=1,1,0&chm=o,990000,0,20,4&chxt=r,x,y&chxs=0,990000,11,0,_|1,990000,1,0,_|2,990000,1,0,_&chxl=0:|2232|1:||2:||&chxp=0,42.3">
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>

