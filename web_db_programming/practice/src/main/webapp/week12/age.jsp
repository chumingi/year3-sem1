<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
  <label for="birthyear">출생년도: </label>
  <input type="text" name="birthyear" id="birthyear" />
</form>

<jsp:useBean id="age" class="com.office.practice.week12.Age" />

<%
  int year = request.getParameter("birthyear");
%>
나이: <%= age.getAge(year) %>
</body>
</html>
