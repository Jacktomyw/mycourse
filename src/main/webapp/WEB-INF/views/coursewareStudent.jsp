<%@ page import="com.h.mycourse.model.Courseware" %>
<%@ page import="com.h.mycourse.model.AClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课件列表</title>
</head>
<body>
<%
    AClass aClass = (AClass) request.getAttribute("aClass");
%>
课程名称:<%=aClass.getCourse().getCourseName()%>
<br>
<%
    if(aClass.getCourse().getCoursewares().size()>0){
%>
<table border="1">
    <tr>
        <th>课件名称</th>
        <td>下载</td>
    </tr>
    <%
        for(Courseware courseware : aClass.getCourse().getCoursewares()){
    %>
    <tr>
        <th><%=courseware.getFileName()%></th>
        <%
            out.print("<th><a href=\"/courseware/download?coursewareID="+courseware.getId()+"\">下载</a></th>");
        %>
    </tr>
    <%
        }
    %>
</table>
<%
}else{
%>
<br>本课程还没有上传任何课件！
<%
    }
%>

<%
    out.print("<br><a href=\"/student/classDetail?ID="+aClass.getId()+"\">返回</a>");
%>
<br><a href="/student/myClasses">我的课程</a>
<br><a href="/main">主界面</a>
</body>
</html>
