<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Registration Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/validation.js"/>"></script>
</head>
<body>
<c:url var="addAction" value="/register"/>
<form:form action="${addAction}" commandName="employee" method="post" cssClass="form-horizontal" onsubmit="return checkVal(this);">
    <form:hidden path="id" id="id" cssClass="form-control"/>
    <br><br>
    <div class="form-group">
        <label for="userName" class="col-sm-2 control-label">User Name </label>
        <div class="col-sm-10">
            <form:input path="userName" id="userName" cssClass="form-control" placeholder="User Name" maxlength="10"/>
            <span id="userNameMessage"></span>
        </div>
    </div>

    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password </label>
        <div class="col-sm-10">
            <form:password path="password" id="password" cssClass="form-control" placeholder="Password" maxlength="20"/>
            <span id="passwordMessage"></span>
        </div>
    </div>


    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Name </label>
        <div class="col-sm-10">
            <form:input path="name" id="name" cssClass="form-control" placeholder="Name" maxlength="20"/>
            <span id="nameMessage" class="errmsg"></span>
        </div>
    </div>

    <div class="radio" style="padding-left: 17.2%;">
        <label>
            <form:radiobutton path="gender" name="gender" id="gender_male" value="Male"/>
            Male
        </label>
        <label>
            <form:radiobutton path="gender" name="gender" id="gender_female" value="Female"/>
            Female
        </label>
        <span id="genderMessage" class="errmsg"></span>
    </div>

    <br>

    <div class="form-group">
        <label for="salary" class="col-sm-2 control-label">Salary </label>
        <div class="col-sm-10">
            <form:input path="salary" id="salary" cssClass="form-control" placeholder="Salary" maxlength="10"/>
            <span id="salaryMessage" class="errmsg"></span>
        </div>
    </div>

    <div class="form-group">
        <label for="city" class="col-sm-2 control-label">City </label>
        <div class="col-sm-10">
            <form:select path="city" id="city" cssClass="form-control" items="${cityList}" />
            <span id="cityMessage" class="errmsg"></span>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default" style="background-color: steelblue; color: white">Register</button>
        </div>
    </div>
</form:form>
</body>
</html>