<!DOCTYPE html>
<html>
<head>
    <style>
        #map-canvas img {
            max-width: none;
        }
    </style>

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false">
    </script>
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

    <script type="text/javascript">
        var districtId = 25;
        var map;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var postUrl = 'add';

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 12,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            addNewMarker();
        }
        google.maps.event.addDomListener(window, 'load', initialize);

        function addNewMarker() {
            var markerOptions = {
                position: center,
                draggable:true,
                map:map,
                icon:'https://maps.gstatic.com/mapfiles/ms2/micons/flag.png'
            };

            var marker = new google.maps.Marker(markerOptions);
            google.maps.event.addListener(marker, "dragend", function() {
                var point = marker.getPosition();
                map.panTo(point);
                console.log("dragend");
                console.log("lat:" + point.lat().toFixed(5));
                console.log("lat:" + point.lng().toFixed(5));
                sendData(point.lat().toFixed(5), point.lng().toFixed(5));
            });
        }

        function sendData(lat, lng) {
            $.ajax({
                type: "GET",
                url: postUrl,
                data: "districtId=" + districtId + "&lat=" + lat + "&lng=" + lng,
                success: function(data) {
                }
            });
            return false;
        }
    </script>
</head>
<body>
<div id="map-canvas" style="width:100%; height:59em"/>
</body>
</html>

