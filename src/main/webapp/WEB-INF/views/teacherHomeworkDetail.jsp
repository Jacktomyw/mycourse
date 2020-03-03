<%@ page import="com.h.mycourse.model.Homework" %>
<%@ page import="com.h.mycourse.model.DoHomework" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业详情</title>
</head>
<body>
<%
    Homework homework = (Homework) request.getAttribute("homework");
%>
课程名称：<%=homework.getaClass().getCourse().getCourseName()%>
<br>作业名称：<%=homework.getHomeworkName()%>
<br>作业描述：<%=homework.getDescription()%>
<br>截止时间：<%=homework.getDdl()%>
<br>提交人数：<%=homework.getDoHomework().size()%>
<br>
<%
    if(homework.getDoHomework().size()>0){
%>
<table border="1">
    <caption>已提交作业的名单</caption>
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>作业</td>
        <td>分数</td>
    </tr>
<%
    for(DoHomework doHomework : homework.getDoHomework()){
%>
    <tr>
        <td><%=doHomework.getUser().getNumber()%></td>
        <td><%=doHomework.getUser().getUsername()%></td>
        <td>
            <%
                out.print("<a href=\"/homework/download?doHomeworkID="+doHomework.getId()+"\">下载</a><br>");

            %>
        </td>
        <td>
            <%
                if(doHomework.getScore()==-1){
                    out.print("未打分");
                }else{
                    out.print(doHomework.getScore());
                }
            %>
        </td>
    </tr>
<%
    }
%>
</table>
<%
    }else{
        out.print("该课程暂时没有学生提交作业！");
    }

%>
<br><br><b>上传成绩</b>
<%
    out.print("<form method=\"post\" action=\"/homework/teacher/uploadScore?homeworkID="+homework.getId()+"\" enctype=\"multipart/form-data\">");
%>
<br>上传Excel文件：<br>
<input type="file" name="excel" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"><br><br>
<input type="submit" name="submit" value="上传">
<%
    out.print("<br><a href=\"/homework/teacher?classID="+homework.getaClass().getId()+"\">返回</a><br>");
%>
<a href="/teacher/myCourses">我的课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
