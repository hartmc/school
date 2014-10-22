<%--
  User: clindauer
  Date: 10/21/14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lindauer.controller.IndexServlet" %>
<link rel="stylesheet" type="text/css" href="styles.css"/>

<html>
<head>
    <b>Welcome to <a href="index.html">Lindauer Elementary</a></b>
    <br>
    Home of the fighting Eagles!
    <c:choose>
        <c:when test="${user != null}">
            <br>Hi ${user.getFirstName()} - <a href="logout.html">logout</a>
        </c:when>
    </c:choose>

</head>
<body>
<br/><br/>
