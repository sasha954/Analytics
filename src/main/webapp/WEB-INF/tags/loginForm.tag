<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="popUpLogin" id="popUpLogin">
    <div class="form_wrapper">
        <form action="/Analytics/signIn.d" method="post">
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="Login">
            </div>
            <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password">
            </div>
            <div class="form-group">
              <button type="submit" class="form-btn btn btn-primary"><fmt:message key="index.loginLink"/></button>
            </div>
        </form>
    </div>
</div>