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
    <div class="setting-links">
        <a href="deleteLink.d?siteId=${webSiteId}" class="btn btn-default"><fmt:message key="linkList.setting"/></a>
    </div>
    <my:linkList/>
    <my:addLinkForm/>
</body>
</html>
</fmt:bundle>