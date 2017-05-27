<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
<html>
<head>
    <c:set scope="page" var="pageTitle" value="links_list.title"/>
    <%@include file="/WEB-INF/jspf/directive/head.jspf"%>
</head>
<body>
    <my:siteListSidebar/>
    <my:linkList/>
    <my:addLinkForm/>
</body>
</html>
</fmt:bundle>