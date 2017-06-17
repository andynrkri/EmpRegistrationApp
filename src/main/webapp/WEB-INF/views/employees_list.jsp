<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anandtyagi
  Date: 9/6/17
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees List</title>
    <style type="text/css">
        a.button {
            background-color: #4CAF50; /*	Green	*/
            border: none;
            color: white;
            padding: 5px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            margin-right: 15px;
        }

        a.btn {
            background-color: #f44336;
            border: none;
            color: white;
            padding: 5px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            margin-right: 15px;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body style="padding-left: 4%;">
<h3><b>Employees List</b> (<a href="<c:url value='/home'/>">Click to Add New Employee</a>)</h3>

<c:if test="${!empty employeesList}">
    <table class="table" style="padding-left: 30px">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Salary</th>
            <th>City</th>
            <th>Resume</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${employeesList}" var="emp">
            <tr>
                <td>${emp.id}.</td>
                <td>${emp.userName}</td>
                <td>${emp.password}</td>
                <td>${emp.name}</td>
                <td>${emp.gender}</td>
                <td>${emp.salary}</td>
                <td>${emp.city}</td>
                <td><a href="<c:url	value='/resume/${emp.id}'/>" class="button">Retrieve</a></td>
                <td>
                    <a href="<c:url	value='/edit/${emp.id}'/>" class="button">Edit</a>
                    <a href="<c:url	value='/delete/${emp.id}'/>" class="btn">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
