<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布成绩</title>
</head>
<body>
<%
    AClass aClass = (AClass) request.getAttribute("aClass");
%>
课程名称：<%=aClass.getCourse().getCourseName()%>
<br>班次：<%=aClass.getClassNumber()%>
<br>
<%
    out.print("<form method=\"post\" action=\"/grade/release/upload?classID="+aClass.getId()+"\" enctype=\"multipart/form-data\">");
%>
    上传Excel文件：<br>
    <input type="file" name="excel" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"><br><br>
    <input type="submit" name="submit" value="上传">
<%
    out.print("<br><a href=\"/teacher/courseDetail?ID="+aClass.getCourse().getId()+"\">返回</a><br>");
%>
<a href="/teacher/myCourses">我的课程</a><br>
<a href="/main">主界面</a>
</form>
</body>
</html>
