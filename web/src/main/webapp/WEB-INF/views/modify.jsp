<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tw.core.entity.User" %>
<%@ page import="com.tw.core.Dao.UserDao" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/8/15
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

</head>
<body>

<form action="/web/users/modify" method="post">
    <tr>
        序号 :<input type="text" name="id" value="${user.id}" readonly="readonly"/>
        用户名 :<input type="text" name="name" value="${user.name}"/>
        性别 :<input type="text" name="sex" value="${user.sex}"/>
        年龄 :<input type="text" name="age" value="${user.age}"/>
        密码 :<input type="text" name="password" value="${user.password}">
        工号 :<input type="text" name="employeeId" value="${user.employee.id}" readonly="readonly">
    </tr>
    </br>
    <input type="submit" value="更新"/>
</form>


</body>
</html>
