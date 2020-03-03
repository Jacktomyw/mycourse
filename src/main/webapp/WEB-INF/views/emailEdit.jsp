<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮件编辑</title>
</head>
<body>
<%
    AClass aClass = (AClass) request.getAttribute("aClass");
%>
<form action="/teacher/email/check" method="post" id="email">
    <b>邮件编辑</b><br>
    发送给：所有已选择 <%=aClass.getCourse().getCourseName()%> 课程 <%=aClass.getClassNumber()%> 班次的学生<br>
    主题：<input type="text" name="title"><br>
    内容：<br><textarea form="email" name="content"></textarea><br>
    <%
        out.print("<input type=\"hidden\" name=\"classID\" value=\""+aClass.getId()+"\">");
    %>
    <input type="submit" name="submit" value="群发">
</form>
<%
    out.print("<a href=\"/teacher/courseDetail?ID="+aClass.getCourse().getId()+"\">返回</a><br>");
%>
</body>
</html>
