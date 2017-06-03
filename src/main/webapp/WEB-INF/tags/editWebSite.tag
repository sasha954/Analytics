
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<div class="form-wrapper">
    <form action="editWebSite.d" method="post">
        <div class="form-group">
            <input type="text" name="url" class="form-control" value="${webSite.url}" placeholder="Url">
            <c:if test="${not empty webSiteErrors.url}">
                <p class="text-danger"><fmt:message key="messages.${webSiteErrors.url}"/></p>
            </c:if>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary"><fmt:message key="editWebSite.editBtn"/></button>
        </div>
    </form>
</div>