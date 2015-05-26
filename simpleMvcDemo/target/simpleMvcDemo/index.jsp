<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=GBK" %>
<%@ page import="java.util.*"%>

<html>
<body>
<h2>Hello World!</h2>
<%
    //HttpSession session = request.getSession(true);
    System.out.println(session.getId());
    out.println("<br> SESSION ID:" + session.getId()+"<br>");
    // 如果有新的请求，则添加session属性
    String name = request.getParameter("name");
    if (name != null && name.length() > 0) {
        String value = request.getParameter("value");
        session.setAttribute(name, value);
    }
    out.print("<b>Session List:</b>");
    Enumeration<String> names = session.getAttributeNames();
    while (names.hasMoreElements()) {
        String sname = names.nextElement();
        String value = session.getAttribute(sname).toString();
        out.println( sname + " = " + value+"<br>");
        System.out.println( sname + " = " + value);
    }
%>
<form action="index.jsp" method="post">
    名称:<input type=text size=20 name="name">
    <br>
    值:<input type=text size=20 name="value">
    <br>
    <input type=submit value="提交">
</form>
</body>
</html>
<%--<html>--%>
<%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <%--<title>mvc</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="http://localhost:8080/mvc/user.do" method="post">--%>
    <%--<input type="text"   name="username" value="admin">--%>
    <%--<input type="text"   name="password" value="123456">--%>
    <%--<input type="submit" value="提交数据" >--%>
<%--</form>--%>


<%--</body>--%>
<%--</html>--%>
