
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>

<form action="addWebSite.d" method="post">
    <div class="form-group">
        <input type="text" name="url" class="form-control" placeholder="Url"/>
        <c:if test="${not empty webSiteErrors.url}">
            <p class="text-danger"><fmt:message key="messages.${webSiteErrors.url}"/></p>
        </c:if>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary"><fmt:message key="addWebSite.addBtn"/></button>
    </div>
</form>