<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<jsp:include page="/sidebarList.d"/>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<fmt:bundle basename="i18n">

        <div class="sidebar-list-wrapper panel">
            <div class="sidebar-list-header panel-heading">
                <h4 class="sidebar-list-title"><fmt:message key="siteListSidebar.title"/>(<c:out value="${webSitesCount}"/>) </h4>
                <div class="sidebar-list-setting"><h4><a href="siteList.d" class="glyphicon glyphicon-asterisk"></a> </h4></div>
                <div class="clearfix"></div>
            </div>
            <div class="sidebar-list panel-body">
                <ul>
                    <c:if test="${not empty reqSiteList}">
                        <c:forEach items="${reqSiteList}" var="webSite">
                            <li><a href="viewLinks.d?sid=${webSite.id}"><c:out value="${webSite.url}"/></a></li>
                        </c:forEach>
                    </c:if>
                    <hr>
                    <li><a href="addWebSite.d"><fmt:message key="siteListSidebar.addNewSite"/></a></li>
                </ul>
            </div>

    </div>
</fmt:bundle>