<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<title>主界面</title>
<body>
欢迎，管理员 ${sessionScope.user.getUsername()} ！<br>
您的个人信息：<br>
邮箱：${sessionScope.user.getEmail()}<br>
<a href="/admin/examine">审批课程申请</a><br>
<a href="/admin/stats">查看统计信息</a><br>
<a href="/logout">退出</a><br>
</body>