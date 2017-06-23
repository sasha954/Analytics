<%@include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@tag body-content="empty" language="java" pageEncoding="UTF-8" %>

<div class="links-list table-responsive panel">
    <div class="setting-links panel-heading">
        <a href="deleteLink.d?siteId=${webSiteId}" class="btn btn-default float-right"><fmt:message
                key="linkList.setting"/></a>
        <div class="clearfix"></div>
        <a href="javascript:void(0)" id="addLink" class="btn btn-success float-right">+</a>
        <div class="clearfix"></div>
    </div>
    <table class="table panel-body">
        <c:if test="${not empty reqLinksList}">
            <c:forEach items="${reqLinksList}" var="link">
                    <tr data-href="statistic.d?linkId=${link.id}" class="tr-link">

                        <td><c:out value="${link.url}"/></td>
                        <td><c:out value="${link.name}"/></td>
                        <td><c:out value="?lid=${link.paramForUrlOnWebSite}"/></td>
                        <td><c:out value="${link.countVisiters}"/></td>
                    </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
