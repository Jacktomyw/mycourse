<%@ page import="com.h.mycourse.model.Course" %>
<%@ page import="com.h.mycourse.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程论坛首页</title>
</head>
<body>
<%
    Course course = (Course) request.getAttribute("course");
    List<Post> posts = (List<Post>) request.getAttribute("posts");
%>
    课程名称：<%=course.getCourseName()%>
    <%
        if(posts.size()>0){
    %>
<table border="1">
    <tr>
        <th>主题</th>
        <td>作者</td>
        <td>发布时间</td>
        <td>查看</td>
    </tr>
<%
        for(Post post : posts){
%>
<tr>
    <th><%=post.getTitle()%></th>
    <td><%=post.getAuthor()%></td>
    <td><%=post.getTime()%></td>
    <td><%
        out.print("<a href=\"/bbs/post?ID="+post.getId()+"\">查看</a>");
    %></td>
</tr>
<%
        }
        %>
</table>
        <%
    }else{
%>
<br>当前课程暂时没有帖子！
<%
    }
%>
<br><b>发布新帖子</b>
<form action="/bbs/addPost" method="post" id="postForm">
    主题：<input type="text" name="title">
    <br>内容：<br><textarea name="content" form="postForm"></textarea>
    <%--<br>内容：<input type="text" name="content">--%>
    <br><input type="submit" name="submit" value="发布">
    <input type="hidden" name="courseID" value=<%=course.getId()%>>
</form>
<%
    out.print("<br><a href=\""+request.getAttribute("url")+"\">返回</a>");
    out.print("<br><a href=\""+request.getAttribute("my")+"\">我的课程</a>");
%>
<br><a href="/main">主界面</a>
</body>
</html>
