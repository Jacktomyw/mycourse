<%@ page import="com.h.mycourse.model.User" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
  <head>
    <title>修改个人信息</title>
  </head>
  <body>
  <form action="/updateInfo/update" method="post">
      邮箱：<input type="text" name="email" value=<%=((User)session.getAttribute("user")).getEmail()%>><br>
      姓名：<input type="text" name="username" value=<%=((User)session.getAttribute("user")).getUsername()%>><br>
      <%if(((User) session.getAttribute("user")).isStudent()){%>
      学号：<input type="text" name="number" value=<%=((User)session.getAttribute("user")).getNumber()%>><br>
      <%}%>
      密码：<input type="password" name="password">（留空为不修改）<br>
      <input type="submit" name="update" value="确认">
      <input type="submit" name="cancel" value="取消">
  </form>
  </body>
</html>
