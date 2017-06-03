
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<div class="links-list table-responsive">
<table class="table">
    <c:if test="${not empty reqLinksList}">
        <c:forEach items="${reqLinksList}" var="link">
            <tr>
                <td><c:out value="${link.url}"/></td>
                <td><a class="btn btn-danger" href="deleteLink.d?linkId=${link.id}"><fmt:message key="deleteLink.deleteBtn"/></a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</div>