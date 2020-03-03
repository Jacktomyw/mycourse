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
<%
    if(aClass.getHomework().size()>0){
%>
<table border="1">
    <tr>
        <th>作业名称</th>
        <td>截止日期</td>
        <td>详情</td>
    </tr>
    <%
        for(Homework homework : aClass.getHomework()){
    %>
    <tr>
        <th><%=homework.getHomeworkName()%></th>
        <td><%=homework.getDdl()%></td>
        <td>
            <%
                out.print("<a href=\"/homework/student/detail?ID="+homework.getId()+"\">查看</a><br>");
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
    out.print("<br><a href=\"/student/classDetail?ID="+aClass.getId()+"\">返回</a><br>");
%>
<a href="/student/myClasses">我的课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
