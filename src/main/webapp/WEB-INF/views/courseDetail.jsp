<%@ page import="com.h.mycourse.model.Course" %>
<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程详情</title>
</head>
<body>
课程名称：<%= ((Course)request.getAttribute("course")).getCourseName()%><br>
课程状态：<%
    Course course = (Course)request.getAttribute("course");
    if(course.isRefused()){
        out.print("被拒绝");
    }else{
        if(course.isAvailable()){
            out.print("可发布");
        }else{
            out.print("待审批");
        }
    }
    if(course.isAvailable()){
        out.print("<br><a href=\"/teacher/courseRelease?ID="+course.getId()+"\">发布课程</a>");
%>
<br><%
    out.print("<a href=\"/teacher/courseware?courseID="+course.getId()+"\">查看课件</a>");
%>
<br><%
    out.print("<a href=\"/bbs?courseID="+course.getId()+"\">查看论坛</a>");
%>
<%
        if(course.getClasses().size()>0){
%>
<br><table border="1">
    <caption>班级</caption>
    <tr>
        <th>班次</th>
        <td>开课日期</td>
        <td>结束日期</td>
        <td>限选人数</td>
        <td>已选人数</td>
        <td>状态</td>
        <td>作业</td>
        <td>成绩</td>
        <td>邮件</td>
    </tr>
    <%
            for(AClass aClass : course.getClasses()){
    %>
    <tr>
        <th><%=aClass.getClassNumber()%></th>
        <td><%=aClass.getStartDate()%></td>
        <td><%=aClass.getEndDate()%></td>
        <td><%=aClass.getMaxStudent()%></td>
        <td><%=aClass.getCurrentStudent()%></td>
        <td><%
            if(aClass.isAvailable()){
                if(!aClass.isGradeReleased()){
                    out.print("可选课");
                }else{
                    out.print("已结课");
                }
            }
            else if(aClass.isRefused()) out.print("被拒绝");
            else out.print("审批中");%></td>
        <td><%
            if(aClass.isAvailable()) {
                out.print("<a href=\"/homework/teacher?classID=" + aClass.getId() + "\">查看</a><br>");
            }else out.print("-----");
        %></td>
        <td><%
            if(aClass.isAvailable()){
                    out.print("<a href=\"/grade?classID="+aClass.getId()+"\">查看</a><br>");
            }
            else out.print("-----");
        %></td>
        <td><%
            out.print("<a href=\"/teacher/email?classID="+aClass.getId()+"\">群发</a><br>");
        %></td>
    </tr>
    <%
    }
    %>
</table>
<%
        }else{
%>
<br>当前课程无发布过的班级！
<%
        }
    }
%>
<br>
<a href="/teacher/myCourses">我的课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
