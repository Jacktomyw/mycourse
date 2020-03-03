<%@ page import="com.h.mycourse.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审批</title>
</head>
<body>

    <%
        List<Course> courseList = (List<Course>) request.getAttribute("courses");
        if(courseList.size()>0){
    %>
    <table border="1">
        <caption>待审批课程</caption>
        <tr>
            <th>课程名称</th>
            <td>请求人</td>
            <td>通过</td>
            <td>拒绝</td>
        </tr>
    <%
            for(Course course : courseList){
    %>
    <tr>
        <th><%=course.getCourseName()%></th>
        <td><%=course.getOwner().getUsername()%></td>
        <td><%
                out.print("<a href='/admin/examine/course/pass?ID="+course.getId()+
                        "'>通过</a></td>" +
                        "<td><a href='/admin/examine/course/refuse?ID="+course.getId()+"'>拒绝</a>"
        );
        %></td>
    </tr>
        <%
            }
        %>
    </table><br>
    <%
        }else{
    %>
        无待审批课程！<br>
    <%
        }
    %>

    <%
        List<AClass> ClassList = (List<AClass>) request.getAttribute("classes");
        if(ClassList.size()>0){
    %>
    <table border="1">
        <caption>待审批班级</caption>
        <tr>
            <th>课程名称</th>
            <td>请求人</td>
            <td>开课日期</td>
            <td>结束日期</td>
            <td>班次</td>
            <td>限选人数</td>
            <td>通过</td>
            <td>拒绝</td>
        </tr>
        <%
            for(AClass aClass : ClassList){
        %>
    <tr>
        <th><%=aClass.getCourse().getCourseName()%></th>
        <td><%=aClass.getCourse().getOwner().getUsername()%></td>
        <td><%=aClass.getStartDate()%></td>
        <td><%=aClass.getEndDate()%></td>
        <td><%=aClass.getClassNumber()%></td>
        <td><%=aClass.getMaxStudent()%></td>
        <td><%
                out.print("<a href='/admin/examine/class/pass?ID="+aClass.getId()+
                        "'>通过</a></td>" +
                        "<td><a href='/admin/examine/class/refuse?ID="+aClass.getId()+"'>拒绝</a>"
                        );
            %></td>
    </tr>
        <%
            }
            %>

    </table><br>
    <%
        }else{
    %>
        无待审批班级！<br>
    <%
        }
    %>
<a href="/main">主界面</a>
</body>
</html>
