<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 17-5-10
  Time: 下午4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
%>
<%
    String title = (String) request.getAttribute("title");
    String content = (String) request.getAttribute("content");
    if (title != null) {
        out.println("<span class='ti'>" + title + "</span>");
    }
    if (content != null) {
        out.println("<span class='ct'>" + content + "</span>");
    }
%>

<form action="/MessageServlet" name="for" method="post">
    标题<input type="text" name="title"/>
    内容<input type="textarea" name="content"/>
    <input type="submit" value="提 交"/>

</form>
</body>
</html>
