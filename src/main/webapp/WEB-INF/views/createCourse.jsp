<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建新课程</title>
</head>
<body>
<%if(request.getAttribute("msg")!=null) out.println(request.getAttribute("msg"));%>
    <form action="/teacher/createCourse/check" method="post">
        课程名称：<input type="text" name="courseName"><br>
        <input type="submit" name="confirm" value="确认">
        <input type="submit" name="cancel" value="取消">
    </form>
</body>
</html>
