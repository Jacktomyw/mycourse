<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page import="com.h.mycourse.model.Grade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成绩情况</title>
</head>
<body>
<%
    AClass aClass = (AClass) request.getAttribute("aClass");
%>
    课程名称：<%=aClass.getCourse().getCourseName()%>
<br>课程班次：<%=aClass.getClassNumber()%>
<br>
<table border="1">
    <tr>
        <th>学号</th>
        <td>姓名</td>
        <td>成绩</td>
    </tr>
    <%
        for(Grade grade : aClass.getGrades()){
    %>
    <tr>
        <th><%=grade.getUser().getNumber()%></th>
        <td><%=grade.getUser().getUsername()%></td>
        <td>
            <%
                if (grade.getGrade() == -2) {
                    out.print("已退课");
                }else{
                    if(aClass.isGradeReleased()) {
                        if (grade.getGrade() == -1) {
                            out.print("0（无成绩）");
                        } else {
                            out.print(grade.getGrade());
                        }
                    }else{
                        out.print("未发布");
                    }
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    out.print("<a href=\"/grade/release?classID="+aClass.getId()+"\">更新成绩</a><br>");
    out.print("<br><a href=\"/teacher/courseDetail?ID="+aClass.getCourse().getId()+"\">返回</a>");
%>
<br>
<a href="/teacher/myCourses">我的课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
