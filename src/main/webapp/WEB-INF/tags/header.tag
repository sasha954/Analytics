
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ tag body-content="empty" language="java" pageEncoding="UTF-8"%>
<fmt:bundle basename="i18n">
<div class="row">
    <div class="col-lg-12">
        <header class="main-header">
            <div class="logo float-left">
               <h3>Link Analytics</h3>
            </div>
            <div class="main-menu">
                <nav class="navigation-bar">
                    <c:if test="${pageContext.request.getSession().getAttribute(\"sessionUser\") != null}">
                        <ul class="navigation-list">

                            <li><a href="javascript:void(0)"><p id="user-name">${user.firstName} ${user.lastName}</p></a></li>
                            <li><a href="logOut.d"><fmt:message key="header.logOut"/> </a></li>

                        </ul>
                    </c:if>
                </nav>
            </div>
            <div class="clearfix"></div>
        </header>
    </div>
</div>
</fmt:bundle>