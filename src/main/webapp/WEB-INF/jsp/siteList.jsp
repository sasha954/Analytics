<%@include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<fmt:setLocale value="${locale}"/>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<fmt:bundle basename="i18n">
<html>
<head>
    <c:set var="pageTitle" value="webSiteList.title" scope="page"/>
    <%@include file="/WEB-INF/jspf/directive/head.jspf"%>
</head>
<body>
    <div class="container">

                <my:header/>

        <div class="row">
            <div class="row">
                <div class="col-lg-3">
                    <my:siteListSidebar/>
                    <my:addLinkForm/>
                </div>
            <div class="col-lg-8 col-lg-offset-1">
                <my:siteTableList/>
            </div>
        </div>
    </div>
</body>
</html>
</fmt:bundle>
