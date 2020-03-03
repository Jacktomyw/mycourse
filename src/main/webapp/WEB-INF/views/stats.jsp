<%@ page import="com.h.mycourse.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.h.mycourse.model.Course" %>
<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page import="com.h.mycourse.model.Grade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyCourse统计信息</title>
</head>
<body>
<%
    List<User> students = (List<User>) request.getAttribute("students");
    List<User> teachers = (List<User>) request.getAttribute("teachers");
%>
    教师数量：<%=teachers.size()%>
<br>学生数量：<%=students.size()%>
<br>
<table border="1">
    <caption>教师详情</caption>
    <tr>
        <th>姓名</th>
        <td>邮箱</td>
        <td>总创建课程数</td>
        <td>创建课程被拒</td>
        <td>总发布班级数</td>
        <td>发布班级被拒</td>
        <td>上传课件数</td>
        <td>发布作业数</td>
    </tr>
    <%
        for(User user : teachers){
    %>
    <tr>
        <th><%=user.getUsername()%></th>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCourses().size()%></td>
        <%
            int countOfRefusedCourses=0;
            int countOfClasses=0;
            int countOfRefusedClasses=0;
            int countOfCoursewares=0;
            int countOfHomework=0;
            for(Course course : user.getCourses()){
                if(course.isRefused())
                    countOfRefusedCourses++;
                countOfClasses+=course.getClasses().size();
                countOfCoursewares+=course.getCoursewares().size();
                for(AClass aClass : course.getClasses()){
                    if(aClass.isRefused()){
                        countOfRefusedClasses++;
                    }
                    countOfHomework+=aClass.getHomework().size();
                }
            }
        %>
        <td><%=countOfRefusedCourses%></td>
        <td><%=countOfClasses%></td>
        <td><%=countOfRefusedClasses%></td>
        <td><%=countOfCoursewares%></td>
        <td><%=countOfHomework%></td>
    </tr>
    <%
        }
    %>
</table>
<br>
<table border="1">
    <caption>学生详情</caption>
    <tr>
        <th>姓名</th>
        <td>邮箱</td>
        <td>总选择课程数</td>
        <td>退课数</td>
        <td>作业提交数</td>
    </tr>
    <%
        for(User user : students){
    %>
    <tr>
        <th><%=user.getUsername()%></th>
        <td><%=user.getEmail()%></td>
        <td><%=user.getGrades().size()%></td>
        <%
            int countOfQuitClasses=0;
            for(Grade grade : user.getGrades()){
                if(grade.getGrade()==-2)
                    countOfQuitClasses++;
            }
        %>
        <td><%=countOfQuitClasses%></td>
        <td><%=user.getDoHomework().size()%></td>
    </tr>
    <%
        }
    %>
</table>
<br><a href="/main">主界面</a>
</body>
</html>
