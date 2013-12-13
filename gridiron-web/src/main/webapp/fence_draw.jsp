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
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false&libraries=drawing">
    </script>
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

    <script type="text/javascript">
        var districtId = ${district.id};
        var map;
        var marker;
        var center = new google.maps.LatLng(1.5243, 103.64988);

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            addDrawingManager();
            addDistrict()
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
                var center = new google.maps.LatLng(district.center.x, district.center.y);
                map.panTo(center);
            });
        }

        function addDrawingManager() {
            var polyOptions = {
                strokeWeight: 0,
                fillOpacity: 0.45,
                editable: true
            };

            var drawingManager = new google.maps.drawing.DrawingManager({
                drawingControl: true,
                drawingControlOptions: {
                    drawingModes: [
                        google.maps.drawing.OverlayType.POLYGON
                    ]
                },
                markerOptions: {
                    draggable: true
                },
                polylineOptions: {
                    editable: true
                },
                rectangleOptions: polyOptions,
                circleOptions: polyOptions,
                polygonOptions: polyOptions,
                map: map
            });

            google.maps.event.addListener(drawingManager, 'overlaycomplete', function(event) {
                if (event.type == google.maps.drawing.OverlayType.POLYGON) {
                    var p = event.overlay.getPath();
                    var i = 0;
                    var str = "POLYGON ((";
                    for (i = 0; i < p.getLength(); i++) {
                        str += p.getAt(i).lat() + " " + p.getAt(i).lng() + ",";
                    }
                    str += p.getAt(0).lat() + " " + p.getAt(0).lng(); // close the polygon
                    str += "))";
                    console.log(str);
                    sendData(str);
                }
            });
        }

        function sendData(polyStr) {
            $.ajax({
                type: "GET",
                url: '/turf/add',
                data: 'polyStr=' + polyStr,
                success: function(data) {
                    alert(data);
                }
            });
            return false;
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas" style="width:100%; height:59em"/>
</body>
</html>

