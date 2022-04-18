<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko" xml:lang="ko">
  <head>
    <title>Home Page</title>
    <meta content="text/html; charset=utf-8" />
  </head>

  <body>
    <h2>Hello World!</h2>

    <hr>
    <sec:authorize access="isAuthenticated()">
      <p>principal : <sec:authentication property="principal" /></p>
      <p>principal : <sec:authentication property="principal.user.usrNm" /></p>
      <p>principal : <sec:authentication property="principal.user.usrPw" /></p>
    </sec:authorize>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
      <input type="submit" value="Logout" />
    </form:form>
  </body>
</html>

