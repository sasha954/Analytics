<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <c:set scope="page" var="pageTitle" value="profile.title"/>
    <%@include file="/WEB-INF/jspf/directive/head.jspf"%>
</head>
<body>
    <div class="container">
        <my:header/>
        <a href="${pageContext.request.contextPath}/logOut.d">logout</a>
            <div class="row">
                <div class="col-lg-3">
                    <my:siteListSidebar/>
                    <my:addLinkForm/>
                </div>
        <h3>${user.email}</h3>
        <h3>${user.firstName}</h3>
        <h3>${user.lastName}</h3>
            </div>
    </div>
</body>
</html>
