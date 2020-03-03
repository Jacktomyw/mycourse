<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择新课程</title>
</head>
<body>
    <%
        List<AClass> classes = (List<AClass>) request.getAttribute("classes");
        if(classes.size()>0){
    %>
    <table border="1">
        <caption>所有课程</caption>
        <tr>
            <th>课程名称</th>
            <td>教师</td>
            <td>班次</td>
            <td>开课日期</td>
            <td>结束日期</td>
            <td>已选人数</td>
            <td>限选人数</td>
            <td>选择</td>
        </tr>
        <%
            for(AClass aClass : classes){
        %>
        <tr>
            <th><%=aClass.getCourse().getCourseName()%></th>
            <td><%=aClass.getCourse().getOwner().getUsername()%></td>
            <td><%=aClass.getClassNumber()%></td>
            <td><%=aClass.getStartDate()%></td>
            <td><%=aClass.getEndDate()%></td>
            <td><%=aClass.getCurrentStudent()%></td>
            <td><%=aClass.getMaxStudent()%></td>
            <td><%out.print("<a href=\"/student/myClasses/selectClass/check?ID="+aClass.getId()+"\">选择</a>");%></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }else{
    %>
    暂时没有可选择的课程！
    <%
        }
    %>
    <br><a href="/student/myClasses">返回我的课程</a>
    <br><a href="/main">主界面</a>
</body>
</html>
