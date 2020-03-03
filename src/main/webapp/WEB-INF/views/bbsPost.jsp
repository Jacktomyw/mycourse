<%@ page import="com.h.mycourse.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.h.mycourse.model.Reply" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖子内容</title>
</head>
<body>
<%
    Post post = (Post) request.getAttribute("post");
    List<Reply> replies = (List<Reply>) request.getAttribute("replies");
%>
    课程名称：<%=post.getCourse().getCourseName()%><br>
    主题：<%=post.getTitle()%><br>
    发帖人：<%=post.getAuthor()%><br>
    发帖时间：<%=post.getTime()%><br>
    内容：<%=post.getContent()%><br>
<%
    if(replies.size()>0){
        for(Reply reply : replies){
%>
<fieldset>
    回复人：<%=reply.getAuthor()%><br>
    回复时间：<%=reply.getTime()%><br>
    内容：<%=reply.getContent()%><br>
</fieldset>
<%
        }
    }else{
%>
该帖子暂无回复。<br>
<%
    }
%>
<br><b>发布新回复</b>
<form action="/bbs/post/addReply" method="post" id="replyForm">
    <%--内容：<input type="text" name="content">--%>
    内容：<br><textarea name="content" form="replyForm"></textarea>
    <br><input type="submit" name="submit" value="发布">
    <input type="hidden" name="postID" value=<%=post.getId()%>>
</form>

<br><%
    out.print("<a href=\"/bbs?courseID="+post.getCourse().getId()+"\">返回</a>");
%>
<br><a href="/main">主界面</a>
</body>
</html>
