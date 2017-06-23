<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<div class="reg-form panel" id="popUpLogin">
    <div class="panel-heading">
        Sign Up
    </div>
    <div class="form_wrapper">
        <form action="/Analytics/registration.d" method="post">
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="Email"/>
                <c:if test="${not empty regErrors.email}">
                    <p class="text-danger"><fmt:message key="messages.${regErrors.mail}"/></p>
                </c:if>
            </div>
            <div class="form-group">
                <input type="password" class="form-control"  name="password" placeholder="Password">
                <c:if test="${not empty regErrors.password}">
                    <p class="text-danger"><fmt:message key="messages.${regErrors.password}"/></p>
                </c:if>
            </div>
            <div class="form-group">
                <input type="password" class="form-control"  name="confirm_password" placeholder="Confirm password">
                <c:if test="${not empty regErrors.confirm_password}">
                    <p class="text-danger"><fmt:message key="messages.${regErrors.confirm_password}"/></p>
                </c:if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"  name="first_name" placeholder="First name">
                <c:if test="${not empty regErrors.firstName}">
                    <p class="text-danger"><fmt:message key="messages.${regErrors.firstName}"/></p>
                </c:if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"  name="last_name" placeholder="Last name">
                <c:if test="${not empty regErrors.lastName}">
                    <p class="text-danger"><fmt:message key="messages.${regErrors.lastName}"/></p>
                </c:if>
            </div>
            <div class="form-group">
                <button class="btn btn-primary form-control" type="submit">
                    Register
                </button>
            </div>

        </form>
    </div>
</div>