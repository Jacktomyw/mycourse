<%@ page import="java.util.List" %>
<%@ page import="com.h.mycourse.model.Grade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的课程</title>
</head>
<body>
<%
    List<Grade> grades = (List<Grade>) request.getAttribute("grades");
    if(grades.size()>0){
%>
<table border="1">
    <caption>我的课程</caption>
    <tr>
        <th>课程名称</th>
        <td>教师</td>
        <td>班次</td>
        <td>开课日期</td>
        <td>结束日期</td>
        <td>分数</td>
        <td>退课</td>
        <td>详情</td>
    </tr>
    <%
        for(Grade grade : grades){
    %>
    <tr>
        <th><%=grade.getaClass().getCourse().getCourseName()%></th>
        <td><%=grade.getaClass().getCourse().getOwner().getUsername()%></td>
        <td><%=grade.getaClass().getClassNumber()%></td>
        <td><%=grade.getaClass().getStartDate()%></td>
        <td><%=grade.getaClass().getEndDate()%></td>
        <td>
            <%
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
            %>
        </td>
        <td><%
            if(grade.getGrade()!=-2) {
                out.print("<a href=\"/student/myClasses/quitClass?ID=" + grade.getaClass().getId() + "\">退课</a>");
            }else{
                out.print("-----");
            }
        %></td>
        <td><%
            if(grade.getGrade()!=-2) {
                out.print("<a href=\"/student/classDetail?ID=" + grade.getaClass().getId() + "\">详情</a>");
            }else{
                out.print("-----");
            }
        %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }else{
%>
您还没有选择任何课程！<br>
<%
    }
%>
<a href="/student/myClasses/selectClass">选择新课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
