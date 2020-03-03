<%@ page import="com.h.mycourse.model.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的课程</title>
</head>
<body>

    <%
        List<Course> list = (List<Course>) request.getAttribute("courses");
        if(list.size()>0){
    %>
    <table border="1">
        <caption>我的课程</caption>
        <tr>
            <th>课程名称</th>
            <td>状态</td>
            <td>详情</td>
        </tr>
    <%
            for(Course course : list){
    %>
    <tr>
        <th><%=course.getCourseName()%></th>
        <td><%if(course.isAvailable()){
            out.print("可发布");
        }else{
            if(course.isRefused()){
                out.print("被拒绝");
            }else{
                out.print("审批中");
            }
        }
        out.print("</td>"+
        "<td><a href='/teacher/courseDetail?ID="+course.getId()+
        "'>查看</a></td>"
        );
        %>
    </tr>
    <%
            }
    %>
    </table><br>
    <%
        }else{
            out.println("您还没有创建过课程！<br>");
        }
    %>

<a href="/teacher/createCourse">创建新课程</a><br>
<a href="/main">主界面</a>
</body>
</html>
