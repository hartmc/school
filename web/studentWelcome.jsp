<%--
  @author: clindauer
  @date: 10/20/14
--%>
<%@ page import="com.lindauer.model.Student" %>
<%@ page import="com.lindauer.model.Classroom" %>
<%@ include file="header.jsp" %>
<h3>Welcome student ${student.getFirstName()} ${student.getLastName()}</h3>
<br>
<p>Here's a list of your classes:</p>
<br>
<table border="1">
    <tr>
        <td>Class Name</td>
        <td>Room</td>
        <td>Teacher</td>
    </tr>
    <c:forEach items="${classList}" var="classroom">
        <tr>
            <td align="left">
                <a href="classroom.html?id=${classroom.getId()}">${classroom.getName()}</a>
            </td>
            <td align="left">
                ${classroom.getId()}
            </td>
            <td align="left">
                ${classroom.getTeacher().getFirstName()} ${classroom.getTeacher().getLastName()}
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
