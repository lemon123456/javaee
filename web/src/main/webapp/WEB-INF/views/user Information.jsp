<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/7/15
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>用户管理</title>
</head>
<body>

   <ul>
   <li> <a href="/web/logout">logout</a> </li>
   </ul>
    <table border="1">
      <tr>
        <td>姓名</td>
        <td>性别</td>
        <td>年龄</td>
        <td>密码</td>
        <td>工号</td>
        <td>删除用户</td>
        <td>修改信息</td>
      </tr>


    <c:forEach items="${userList}" var="user">
      <tr>
        <td>${user.name}</td>
        <td>${user.sex}</td>
        <td>${user.age}</td>
        <td>${user.password}</td>
        <td>${user.employeeId}</td>
        <td><a href="./users/delete?id=${user.id}">删除用户</a></td>
        <td><a href="./users/modify?id=${user.id}">修改信息</a></td>
      </tr>
    </c:forEach>


      <ul>
        <li><a href="./users/insert">增加用户</a></li>
      </ul>
    </table>
</body>
</html>


