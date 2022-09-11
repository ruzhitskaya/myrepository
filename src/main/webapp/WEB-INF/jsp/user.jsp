<%--
  Created by IntelliJ IDEA.
  User: Елена
  Date: 04.08.2022
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Personal User Page</title>
</head>
<style>
    <%--    Add css for table, rows, column here--%>
    table, tr, th, td {border: blue 1px solid;
        border-radius: 2px;
        background: aqua;
    }
</style>
<body>
Title hello
<div>
    <b>Hello, ${userName}</b>
</div>
<div>
    <h1>System Users</h1>
</div>
<div>
    <table>
        <tr>
            <td>User Id</td>
            <td>User Name</td>
            <td>User Surname</td>
            <td>Birth date</td>
            <td>Is Deleted</td>
            <td>Created</td>
            <td>Changed</td>
            <%--              <td>Weight</td>--%>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
<%--        <c:forEach var="user" items="${users}">--%>
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.surname}</td>
                <td>${user.birth}</td>
                <td>${user.isDeleted}</td>
                <td><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td><fmt:formatDate value="${user.modificationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <%--                  <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${user.weight}"/></td>--%>
                <td>
                    <button>Edit Profile Info</button>
                </td>
                <td>
                    <button>Delete User</button>
                </td>
            </tr>
<%--        </c:forEach>--%>
    </table>
</div>

</body>
</html>

