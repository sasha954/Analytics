<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${locale}"/>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<c:set var="pathContext" value="${pageContext.request.contextPath}"/>
<fmt:bundle basename="i18n">

    <html>
    <head>
        <c:set var="pageTitle" value="index.title" scope="page"/>
        <%@include file="/WEB-INF/jspf/directive/head.jspf" %>
    </head>
    <body>
    <div class="container">

        <my:header/>
        <my:loginForm/>

    </div>
    <%@include file="/WEB-INF/jspf/directive/footer.jspf" %>

    </body>
    </html>
</fmt:bundle>