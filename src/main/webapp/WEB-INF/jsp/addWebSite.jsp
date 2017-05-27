<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
<html>
<head>
    <c:set var="pageTitle" value="addWebSite.title" scope="page"/>
    <%@include file="/WEB-INF/jspf/directive/head.jspf"%>
</head>
<body>
    <my:addSiteForm/>
</body>
</html>
</fmt:bundle>