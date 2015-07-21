<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: twer
  Date: 7/19/15
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程管理</title>
</head>
<body>

  <ul>
    <li>
      <td><a href="/web/">&nbsp;&nbsp;首页&nbsp;&nbsp;</a></td>
      <td><a href="/web/users">&nbsp;&nbsp;用户管理&nbsp;&nbsp;</a></td>
      <td><a href="/web/employee">&nbsp;&nbsp;员工管理&nbsp;&nbsp;</a></td>
      <td><a href="/web/courses">&nbsp;&nbsp;课程管理&nbsp;&nbsp;</a></td>
      <td><a href="/web/customer">&nbsp;&nbsp;顾客管理&nbsp;&nbsp;</a></td>
      <td> <a href="/web/login/logout">&nbsp;&nbsp;logout&nbsp;&nbsp;</a> </td>
    </li>
  </ul>

  <ul>
    <li>课程管理页面</li>
  </ul>


  <table border="1">
    <tr>
      <td>编号</td>
      <td>课程</td>
      <td>教练姓名</td>
      <td>教练ID</td>
      <td>时间</td>
      <td>删除</td>
      <td>更新</td>
    </tr>

    <c:forEach items="${courseList}" var="course">
      <tr>
        <td>${course.id}</td>
        <td>${course.courseName}</td>
        <td>${course.employee.name}</td>
        <td>${course.employee.id}</td>
        <td>${course.time}</td>
        <td><a href="./courses/delete/${course.id}">删除信息</a></td>
        <td><a href="./courses/modify/${course.id}">修改信息</a></td>
      </tr>
    </c:forEach>


  </table>

  <ul><li>添加公共课</li></ul>

  <form action="/web/courses/insert" method="post">
    <table border="1">
      <tr>
        <td>课程</td>
        <td>教练ID</td>
        <td>时间</td>
      </tr>

      <tr>
        <td><input type="text" name="courseName" value=""></td>
        <td><input type="text" name="coachId" value=""></td>
        <td><input type="text" name="time" value="">
      </tr>
    </table>

    <input type="submit" value="提交"/>
    <input type="reset" value="清除">
  </form>

  <ul><li>预约私教课</li></ul>

  <form action="/web/courses/insert" method="post">
    <table border="1">
      <tr>
        <td>课程</td>
        <td>教练ID</td>
        <td>时间</td>
      </tr>

      <tr>
        <td><input type="text" name="courseName" value="private"></td>
        <td><input type="text" name="coachId" value=""></td>
        <td><input type="text" name="time" value="">
      </tr>
    </table>

    <input type="submit" value="提交"/>
    <input type="reset" value="清除">
  </form>

</body>
</html>