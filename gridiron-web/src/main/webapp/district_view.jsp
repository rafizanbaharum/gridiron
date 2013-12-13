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
        var districtId = ${district.getId()};
        var map;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var data = new google.maps.MVCArray();

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 13,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

            addDistrict();
//            addTurfs();
            addEvents();
        }

        function addDistrict() {
            $.getJSON('/district/findDistrict?id=' + districtId, function(district) {
                var polyOptions = {
                    strokeColor: '#0000FF',
                    strokeOpacity: 0.5,
                    strokeWeight: 2,
                    fillColor: '#0000FF',
                    fillOpacity: 0.01,
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

        function addTurfs() {
            $.getJSON('/turf/findAllTurfs?districtId=' + districtId, function(turfs) {
                for (var i = 0; i < turfs.length; i++) {
                    var turf = turfs[i];
                    var polyOptions = {
                        strokeColor: '#0000FF',
                        strokeOpacity: 0.5,
                        strokeWeight: 2,
                        fillColor: '#0000FF',
                        fillOpacity: 0.02,
                        indexID:turf.id,
                        map:map
                    };
                    var poly = new google.maps.Polygon(polyOptions);
                    var bounds = turf.bounds;
                    for (var j = 0; j < bounds.length; j++) {
                        var latlng = new google.maps.LatLng(bounds[j].x, bounds[j].y);
                        poly.getPath().push(latlng);
                    }
                }
            });
        }

        function addEvents() {
            $.getJSON('/event/findEventsWithinDistrict?districtId=' + districtId, function(events) {
                for (var i = 0; i < events.length; i++) {
                    var event = events[i];
                    latlng = new google.maps.LatLng(event.x, event.y);
                    var marker = new google.maps.Marker({
                        position: latlng,
                        map: map,
                        icon: getCircle(event.headCount)
                    });
                }
            });

            function getCircle(count) {
                return {
                    path: google.maps.SymbolPath.CIRCLE,
                    fillColor: '#0000FF',
                    fillOpacity: .2,
                    scale: (count / 2000),
                    strokeColor: '#0000FF',
                    strokeWeight: .5
                };
            }
        }

        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
<h3>District View: ${district.name} (${district.headCount})</h3>

<div id="map-canvas" style="width:100%; height:25em"></div>
<div id="data" style="width:100%">
    <table class="table table-hover" id="sample-table-1">
        <thead>
        <tr>
            <th class="center">#</th>
            <th>Code</th>
            <th>Name</th>
            <th>HeadCount</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="event" items="${events}" varStatus="idx">
            <tr>
                <td>${idx.count}</td>
                <td>${event.code}</td>
                <td>${event.name}</td>
                <td>${event.headCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

