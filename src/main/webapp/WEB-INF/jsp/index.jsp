<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${locale}"/>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<c:set var="pathContext" value="${pageContext.request.contextPath}"/>
<fmt:bundle basename="i18n">

<html>
<head>
    <c:set var="pageTitle" value="index.title" scope="page"/>
    <%@include file="/WEB-INF/jspf/directive/head.jspf" %>
</head>
<body>
<div class="container">
    <h1>Home page</h1>

    <header>
        <div class="navigationMenu">
            <ul>
                <li><a href="/login.d" id="login"><fmt:message key="index.loginLink"/> </a></li>
                <li><a href="${pathContext}/registration.d"><fmt:message key="index.regLink"/></a></li>
            </ul>
        </div>
        <my:loginForm/>
    </header>
</div>
<%@include file="/WEB-INF/jspf/directive/footer.jspf" %>

</body>
</html>
</fmt:bundle>