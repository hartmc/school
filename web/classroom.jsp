<%--
  User: clindauer
  Date: 10/21/14
--%>

<%@ include file="header.jsp" %>

<h3>Students in ${classroom.getName()} ${classroom.getId()}</h3>
<p class="teacher">Teacher: ${classroom.getTeacher().getFirstName()}
        ${classroom.getTeacher().getLastName()}</p>
<table>
    <c:forEach items="${studentList}" var="student">
        <tr>
            <td align="left">
                    ${student.getFirstName()} ${student.getLastName()}
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="footer.jsp" %>
