<%@include file="/WEB-INF/jspf/directive/head.jspf"%>
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<jsp:include page="/sidebarList.d"/>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<fmt:bundle basename="i18n">
    <div class="sidebar-list-wrapper">
        <div class="sidebar-list-header">
            <h3 class="sidebar-list-title"><fmt:message key="siteListSidebar.title"/>(<c:out value="${webSitesCount}"/>) </h3>
            <div class="sidebar-list-setting"><h2>*</h2></div>
        </div>
        <div class="sidebar-list">
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