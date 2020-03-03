<%@ page import="com.h.mycourse.model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布课程</title>
</head>
<body>
<%
    Course course = (Course) request.getAttribute("course");
%>
<%=request.getAttribute("msg")%>
课程名称：<%=course.getCourseName()%><br>
<form action="/teacher/courseRelease/check" method="post">
    <input type="hidden" name="courseId" value=<%=course.getId()%>>
    开课日期：<input type="date" name="startDate"><br>
    结束日期：<input type="date" name="endDate"><br>
    班次：<input type="number" name="classNumber"><br>
    限选人数：<input type="number" name="maxStudent"><br>
    <input type="submit" name="submit" value="确认">
    <input type="submit" name="cancel" value="取消">
</form>

</body>
</html>
