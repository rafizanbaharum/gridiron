<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

</head>
<body>
<h3>List of Nodes</h3>

<div id="data" style="width:100%">
    <table class="table table-hover" id="sample-table-1">
        <thead>
        <tr>
            <th class="center">#</th>
            <th>Phone</th>
            <th>Name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="node" items="${nodes}" varStatus="idx">
            <tr>
                <td>${idx.count}</td>
                <td>${node.phone}</td>
                <td><a href="${pageContext.request.contextPath}/node/navigate/${node.id}">${node.name}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

