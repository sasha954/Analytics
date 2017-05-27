<%@ include file="/WEB-INF/jspf/directive/head.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>

<form action="/Analytics/registration.d" method="post">
    <div>
        <input type="email" name="email" placeholder="mail"/>
        <c:if test="${not empty regErrors.email}">
            <p class="text-danger"><fmt:message key="messages.${regErrors.mail}"/></p>
        </c:if>
    </div>
    <div>
        <input type="password" name="password" placeholder="pass">
        <c:if test="${not empty regErrors.password}">
            <p class="text-danger"><fmt:message key="messages.${regErrors.password}"/></p>
        </c:if>
    </div>
    <div>
        <input type="password" name="confirm_password" placeholder="re pass">
        <c:if test="${not empty regErrors.confirm_password}">
            <p class="text-danger"><fmt:message key="messages.${regErrors.confirm_password}"/></p>
        </c:if>
    </div>
    <div>
        <input type="text" name="first_name" placeholder="fname">
        <c:if test="${not empty regErrors.firstName}">
            <p class="text-danger"><fmt:message key="messages.${regErrors.firstName}"/></p>
        </c:if>
    </div>
    <div>
        <input type="text" name="last_name" placeholder="lname">
        <c:if test="${not empty regErrors.lastName}">
            <p class="text-danger"><fmt:message key="messages.${regErrors.lastName}"/></p>
        </c:if>
    </div>
    <div>
        <button class="btn btn-primary form-control" type="submit">
            register
        </button>
    </div>

</form>