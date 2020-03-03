<%@ page import="com.h.mycourse.model.Course" %>
<%@ page import="com.h.mycourse.model.Courseware" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课件列表</title>
</head>
<body>
<%
    Course course = (Course) request.getAttribute("course");
%>
课程名称:<%=course.getCourseName()%>
<br>
<%
    if(course.getCoursewares().size()>0){
%>
<table border="1">
    <tr>
        <th>课件名称</th>
        <td>下载</td>
        <td>删除</td>
    </tr>
    <%
        for(Courseware courseware : course.getCoursewares()){
    %>
    <tr>
        <th><%=courseware.getFileName()%></th>
        <%
            out.print("<th><a href=\"/courseware/download?courseID="+course.getId()+"&coursewareID="+courseware.getId()+"\">下载</a></th>");
            out.print("<th><a href=\"/teacher/courseware/delete?courseID="+course.getId()+"&coursewareID="+courseware.getId()+"\">删除</a></th>");
        %>
    </tr>
    <%
        }
    %>
</table>
<%
    }else{
%>
<br>本课程还没有上传任何课件！
<%
    }
%>
<br>上传新课件：
<%
    out.print("<form method=\"post\" action=\"/teacher/courseware/upload?courseID="+course.getId()+"\" enctype=\"multipart/form-data\">");
%>
    <input type="file" name="file"><br><br>
    <input type="submit" name="submit" value="上传">
</form>
<%
    out.print("<a href=\"/teacher/courseDetail?ID="+course.getId()+"\">返回</a><br>");
%>
<a href="/teacher/myCourses">我的课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
