<%--
  @author: clindauer
  @date: 10/21/14

--%>
<%@ page import="com.lindauer.model.Teacher" %>
<%@ page import="com.lindauer.model.Classroom" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>
<h3>Welcome teacher ${teacher.getFirstName()} ${teacher.getLastName()}</h3>
<br>
<p>Here's a list of your classrooms:</p>
<br>
<table border="1">
    <tr>
        <td>Class Name</td>
        <td>Room</td>
    </tr>
    <c:forEach items="${classList}" var="classroom">
        <tr>
            <td>
                <a href="classroom.html?id=${classroom.getId()}">${classroom.getName()}</a>
            </td>
            <td>
                ${classroom.getId()}
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="footer.jsp" %>
