<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="i18n">
    <html>
    <head>
        <c:set var="pageTitle" value="Statistic" scope="page"/>
        <%@include file="/WEB-INF/jspf/directive/head.jspf" %>
    </head>
    <body>
    <div class="container">
        <div class="row"><my:header/></div>
        <div class="row">
            <div class="col-lg-3">
                <my:siteListSidebar/>
                <my:addLinkForm/>
            </div>
            <div class="col-lg-8 col-lg-offset-1">
                <div class="links-list table-responsive panel">
                    <div class="setting-links panel-heading">
                        Link ${link.url} | ${link.name}
                    </div>
                    <table class="table panel-body">
                        <c:if test="${not empty statistics}">
                            <c:forEach items="${statistics}" var="statistic">
                                <tr>
                                    <td>

                                        <c:out value="${statistic.visiters.country}"/>
                                    </td>
                                    <td>

                                        <c:out value="${statistic.visiters.region}"/>
                                    </td>
                                    <td>

                                        <c:out value="${statistic.visiters.city}"/>
                                    </td>
                                    <td>

                                        <c:out value="${statistic.countVisitor}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>