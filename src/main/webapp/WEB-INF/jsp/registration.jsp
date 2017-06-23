<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <c:set var="pageTitle" value="req_page.title" scope="page"/>
    <%@include file="/WEB-INF/jspf/directive/head.jspf"%>
</head>
<body>
<div class="container">
    <my:header/>
    <my:registrationForm/>
</div>
</body>
</html>
