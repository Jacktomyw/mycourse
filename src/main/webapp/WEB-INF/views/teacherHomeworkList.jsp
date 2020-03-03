<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page import="com.h.mycourse.model.Homework" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业列表</title>
</head>
<body>
<%
    AClass aClass = (AClass) request.getAttribute("aClass");
%>
课程名称：<%=aClass.getCourse().getCourseName()%><br>
班级人数：<%=aClass.getCurrentStudent()%><br>
<%
    if(aClass.getHomework().size()>0){
%>
<table border="1">
    <tr>
        <th>作业名称</th>
        <td>截止日期</td>
        <td>提交人数</td>
        <td>详情</td>
    </tr>
<%
        for(Homework homework : aClass.getHomework()){
%>
<tr>
    <th><%=homework.getHomeworkName()%></th>
    <td><%=homework.getDdl()%></td>
    <td><%=homework.getDoHomework().size()%></td>
    <td>
    <%
        out.print("<a href=\"/homework/teacher/detail?ID="+homework.getId()+"\">查看</a><br>");
    %></td>
</tr>
<%
        }
%>
</table>
<%
    }else{
%>
该课程还没有布置过作业！
<%
    }
%>
<br><b>布置新作业：</b>
<%
    out.print("<form action=\"/homework/teacher/addNewHomework?classID="+aClass.getId()+"\" method=\"post\" id=\"newHomework\">" +
            "    作业名：<input type=\"text\" name=\"homeworkName\"><br>" +
            "    描述：<textarea name=\"description\" form=\"newHomework\"></textarea><br>" +
            "    截止日期：<input type=\"date\" name=\"ddlDate\"><input type=\"time\" name=\"ddlTime\"><br>" +
            "    <input type=\"submit\" name=\"submit\" value=\"发布\">" +
            "</form>");
    out.print("<br><a href=\"/teacher/courseDetail?ID="+aClass.getCourse().getId()+"\">返回</a><br>");
%>
<a href="/teacher/myCourses">我的课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
