<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,500,700,600' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
    <title><tiles:getAsString name="title" /></title>
</head>
<body>
<tiles:insertAttribute name="header"/>
<div class="page-content">
    <tiles:insertAttribute name="body"/>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>