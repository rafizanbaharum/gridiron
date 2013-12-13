<!DOCTYPE html>
<html>
<head>
    <style>
        #map-canvas img {
            max-width: none;
        }
    </style>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false&libraries=visualization">
    </script>
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

    <script type="text/javascript">
        var districtId = ${request}
        var map;
        var marker;
        var heatmap;
        var polyline;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var data = new google.maps.MVCArray();

        google.load("visualization", "1", {packages:["corechart"]});

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 14,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

            var markerOptions = {
                position: center,
                draggable:true,
                map:map
            };
            marker = new google.maps.Marker(markerOptions);

            addNodes();
            addPolylines();
            google.maps.event.addListener(marker, 'click', function() {
                addChart(this);
            });
        }

        function addPolylines() {
            var polyOptions = {
                strokeColor: '#000000',
                strokeOpacity: 1.0,
                strokeWeight: 3
            };
            poly = new google.maps.Polyline(polyOptions);
            poly.setMap(map);

            $.getJSON('/district/find?id=' + districtId, function(points) {
                for (var i = 0; i < points.length; i++) {
                    var latlng = new google.maps.LatLng(points[i].latitude, points[i].longitude);
                    poly.getPath().push(latlng);
                }
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
                    'rgba(255, 0, 0, 1)'                ]
            });

            $.getJSON('/node/findNodesWithinDistrict?districtId=' + districtId, function(nodes) {
                for (var i = 0; i < nodes.length; i++) {
                    var latlng = new google.maps.LatLng(nodes[i].latitude, nodes[i].longitude);
                    data.push(latlng);
                }
            });
        }

        function addChart(marker) {
            // Create the data table.
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
            var options = {'title':'Kecenderungan Kawasan',
                'width':250,
                'height':100};

            var node = document.createElement('div');
            var infoWindow = new google.maps.InfoWindow();
            var chart = new google.visualization.PieChart(node);

            chart.draw(data, options);
            infoWindow.setContent(node);
            infoWindow.open(marker.getMap(), marker);
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas" style="width:100%; height:59em"/>
</body>
</html>

