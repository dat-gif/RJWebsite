<%-- 
    Document   : Pagination
    Created on : Jan 18, 2022, 2:05:27 PM
    Author     : Admin
--%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>

        <nav aria-label="...">
            <form action="seachingjob" method="post">
                <ul class="pagination">
                    <c:forEach begin="1" end="${param.totalPage}" var="i">
                        <li class="page-item"><a class="page-link" href="#">${i}</a></li>    
                        </c:forEach>
                    <!--                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                        <li class="page-item active" aria-current="page">
                                            <a class="page-link" href="${param.text2}" id="test" >${param.text2}</a>
                                        </li>-->

                </ul>
            </form>
        </nav>
    </body>
</html>
