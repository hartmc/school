<%--
  @author: clindauer
  @date: 10/19/14
--%>
<%@ include file="header.jsp" %>

  <form action="loginServlet" method="post">

          <table>
              <thead>
              <tr>
                  <th colspan="2" align="center">Classroom Login</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                  <td>Username</td>
                  <td><input type="text" name="username" value="" /></td>
              </tr>
              <tr>
                  <td>Password</td>
                  <td><input type="password" name="password" value="" /></td>
              </tr>
              <tr>
                  <td colspan="2"><input type="submit" value="Login" /></td>

              </tr>
             <!-- could add this later...
              <tr>
                  <td colspan="2">Forgot Password? <a href="resetPassword.jsp">Reset password</a></td>
              </tr>
              <tr>
                  <td colspan="2">Not Registered? <a href="register.jsp">Register now</a></td>
              </tr>
              -->
              </tbody>
          </table>

  </form>

<%@ include file="footer.jsp" %>
