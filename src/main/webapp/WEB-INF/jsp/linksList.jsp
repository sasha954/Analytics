<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
    <html>
    <head>
        <c:set scope="page" var="pageTitle" value="linkList.title"/>
        <%@include file="/WEB-INF/jspf/directive/head.jspf" %>
    </head>
    <body>
    <div class="container">
        <my:header/>
        <div class="row">
            <div class="col-lg-3">
                <my:siteListSidebar/>
                <my:addLinkForm/>
            </div>
            <div class="col-lg-8">
                <div class="setting-links">
                    <a href="deleteLink.d?siteId=${webSiteId}" class="btn btn-default float-right"><fmt:message
                            key="linkList.setting"/></a>
                    <div class="clearfix"></div>
                </div>
                <my:linkList/>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>