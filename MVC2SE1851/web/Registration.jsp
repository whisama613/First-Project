<%-- 
    Document   : Registration
    Created on : Mar 7, 2024, 4:30:21 PM
    Author     : whisa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Registration</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="eroors" value="${requestScope.CREATE_ERRORS}"/>
            Usernames* : <input type="text" name="txtUsername" value="${param.username}}" /> (6 - 20 chars)<br/>
            <c:if test="${not empty errors.usenameLenghError}">
                <font color="red">
                    ${error.usenameLenghError}
                </font><br/>
            </c:if>
                 <c:if test="${not empty errors.usenameIsExisted}">
                <font color="red">
                    ${error.usenameIsExisted}
                </font><br/>
            </c:if>
                Password* : <input type="password" name="txtPassword" value="${param.password}" /> (6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLenghError}">
                <font color="red">
                    ${error.passwordLenghError}
                </font><br/>
            </c:if>
            Confirm* : <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${error.confirmNotMatched}
                </font><br/>
            </c:if>
            FullName* : <input type="text" name="txtFullName" value="" /> (2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullNameLenghError}">
                <font color="red">
                    ${error.fullNameLenghError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="reset" />
        </form>
        <br/>
        <a href="login.html">Return to Login page</a>
    </body>
</html>
