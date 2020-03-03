<%@ page import="com.h.mycourse.model.Homework" %>
<%@ page import="com.h.mycourse.model.DoHomework" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业详情</title>
</head>
<body>
<%
    Homework homework = (Homework) request.getAttribute("homework");
    DoHomework doHomework = (DoHomework) request.getAttribute("doHomework");
%>
课程名称：<%=homework.getaClass().getCourse().getCourseName()%>
<br>作业名称：<%=homework.getHomeworkName()%>
<br>作业描述：<%=homework.getDescription()%>
<br>截止时间：<%=homework.getDdl()%>
<br>状态：<%
    if(doHomework==null){
        out.print("未提交");
        if(homework.getDdl().after(new Timestamp(System.currentTimeMillis()))) {
            out.print("<br><b>提交作业</b><br>" +
                    "<form action=\"/homework/student/upload?homeworkID=" + homework.getId() + "\" method=\"post\" enctype=\"multipart/form-data\">" +
                    "    <input type=\"file\" name=\"homeworkFile\"><br>" +
                    "    <input type=\"submit\" name=\"submit\" value=\"提交\">" +
                    "</form>");
        }
    }else {
        out.print("已提交");
        out.print("<br><a href=\"/homework/download?doHomeworkID=" + doHomework.getId() + "\">下载提交的作业</a><br>");
        out.print("<br>成绩：");
        if(doHomework.getScore()==-1){
            out.print("未打分");
        }else{
            out.print(doHomework.getScore());
        }
        if(homework.getDdl().after(new Timestamp(System.currentTimeMillis()))&&doHomework.getScore()==-1) {
            out.print("<br><b>修改提交的作业</b><br>" +
                    "<form action=\"/homework/student/update?doHomeworkID=" + doHomework.getId() + "\" method=\"post\" enctype=\"multipart/form-data\">" +
                    "    <input type=\"file\" name=\"homeworkFile\"><br>" +
                    "    <input type=\"submit\" name=\"submit\" value=\"提交\">" +
                    "</form>");
        }
    }
    out.print("<br><a href=\"/homework/student?classID="+homework.getaClass().getId()+"\">返回</a><br>");
%>
<a href="/student/myClasses">我的课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
