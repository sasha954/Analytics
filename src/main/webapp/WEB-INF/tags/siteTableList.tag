
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<div class="site-list table-responsive">
    <table class="table">
        <thead>
            <tr>
                <th></th>
                <th><fmt:message key="webSite.title"/></th>
                <th></th>
                <th>${webSiteCount}</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${reqSiteList}" var="webSite">
            <tr class="web-site">
                <td></td>
                <td>${webSite.url}</td>
                <td><a href="editWebSite.d?siteId=${webSite.id}" class="btn btn-primary">Редактировать</a></td>
                <td><a href="deleteSite.d?siteId=${webSite.id}" class="btn btn-danger">Удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>