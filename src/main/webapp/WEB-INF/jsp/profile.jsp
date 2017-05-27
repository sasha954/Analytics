<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/logOut.d">logout</a>
    <my:siteListSidebar/>
    <h3>${user.email}</h3>
    <h3>${user.firstName}</h3>
    <h3>${user.lastName}</h3>
</body>
</html>
