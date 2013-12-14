<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

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
        var routeId = ${route.id};
        var map;
        var marker;
        var data = new google.maps.MVCArray();

        function initialize() {
            var mapOptions = {
                zoom: 14,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            addRoute();
        }

        function addRoute() {
            $.getJSON('${pageContext.request.contextPath}/route/findRoute?routeId=' + routeId, function(route) {
                var polyOptions = {
                    strokeColor: '#0000FF',
                    strokeOpacity: 0.5,
                    strokeWeight: 2,
                    indexID:route.id,
                    map:map
                };
                var poly = new google.maps.Polyline(polyOptions);
                var path = route.path;
                for (var j = 0; j < path.length; j++) {
                    var latlng = new google.maps.LatLng(path[j].x, path[j].y);
                    poly.getPath().push(latlng);
                }
                var center = new google.maps.LatLng(route.center.x, route.center.y);
                map.panTo(center);
            });
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<h3>Navigate Node: ${node.name}</h3>

<div id="map-canvas" style="width:100%; height:20em"></div>
<div id="data" style="width:100%">
    <table class="table table-hover" id="sample-table-1">
        <thead>
        <tr>
            <th class="center">#</th>
            <th>Route</th>
            <th>Length</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="route" items="${nodeRoutes}" varStatus="idx">
            <tr>
                <td>${idx.count}</td>
                <td><a href="${pageContext.request.contextPath}/route/navigate/${route.id}">${route.dateCreated}</a>
                </td>
                <td>${route.length}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

