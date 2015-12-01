<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Moon</title>
</head>
<body>
<h1>Hello!</h1>
<% String msg = "hello, moon";
// Check if we have an argument
if (request.getParameter("name") != null) { %>
<p><strong><% out.print(request.getParameter("name")); %></strong> says:
<% } %>
Hello, moon</p>
</body>
</html>