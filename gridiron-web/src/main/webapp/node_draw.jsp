<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
    <script src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <style>
        #map-canvas img {
            max-width: none;
        }
    </style>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false&libraries=drawing">
    </script>

    <script type="text/javascript">
        var nodeId = ${node.id};
        var map;
        var marker;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 14,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

            var drawingManager = new google.maps.drawing.DrawingManager({
                drawingControl: true,
                drawingControlOptions: {
                    drawingModes: [
                        google.maps.drawing.OverlayType.MARKER
                    ]
                },
                markerOptions: {
                    draggable: true
                },
                map: map
            });


            google.maps.event.addListener(drawingManager, 'overlaycomplete', function(event) {
                if (event.type == google.maps.drawing.OverlayType.MARKER) {
                    var p = event.overlay.position;
                    var i = 0;
                    var paramStr = "nodeId=" + nodeId + "&x=" + p.lat() + "&y=" + p.lng();
                    sendData(paramStr);
                }
            });
        }

        function sendData(coordinateStr) {
            $.ajax({
                type: "GET",
                url: '${pageContext.request.contextPath}/node/beacon',
                data: coordinateStr,
                success: function(data) {
//                    alert(data);
                }
            });
            return false;
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<h3>Add Node</h3>

<div id="map-canvas" style="width:100%; height:59em"/>
</body>
</html>

