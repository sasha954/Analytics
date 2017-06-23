<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<div class="panel form-wrapper">
<div class="panel-heading">
    Добавить ссылку   <a href="javascript:void(0)" id="closeAddForm">x</a>
</div>
<div class="panel-body">
    <form action="addLink.d" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="url" placeholder="URL">
            <c:if test="${not empty addLinkError.url}">
                <p class="text-danger"><fmt:message key="messages.${addLinkError.url}"/></p>
            </c:if>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="name" placeholder="Name">
        </div>
        <input type="hidden" name="site-id" value="${pageContext.request.getParameter("sid")}">
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Добавить</button>
        </div>
    </form>
</div>
</div>