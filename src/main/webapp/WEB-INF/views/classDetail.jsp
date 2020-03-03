<%@ page import="com.h.mycourse.model.Grade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程详情</title>
</head>
<body>
<%
    Grade grade = (Grade)request.getAttribute("grade");
%>
    课程名称：<%=grade.getaClass().getCourse().getCourseName()%><br>
    班次：<%=grade.getaClass().getClassNumber()%><br>
    开课日期：<%=grade.getaClass().getStartDate()%><br>
    结束日期：<%=grade.getaClass().getEndDate()%><br>
    分数： <%

    if(grade.getGrade()==-2){
        out.print("已退课");
    }else{
        if(!grade.getaClass().isGradeReleased()){
            out.print("未发布");
        }
        else{
            if(grade.getGrade()==-1){
                out.print("0（无成绩）");
            }else{
                out.print(grade.getGrade());
            }
        }
    }
%><br>
<%
    out.print("<a href=\"/student/courseware?aClassID="+grade.getaClass().getId()+"\">查看课件列表</a><br>");
    out.print("<a href=\"/bbs?courseID="+grade.getaClass().getCourse().getId()+"\">查看课程论坛</a><br>");
    out.print("<a href=\"/homework/student?classID="+grade.getaClass().getId()+"\">查看作业</a>");
%>

<br><a href="/student/myClasses">返回我的课程</a>
<br><a href="/main">主界面</a>
</body>
</html>
