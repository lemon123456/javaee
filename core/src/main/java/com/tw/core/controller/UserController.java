
package com.tw.core.controller;

import com.mysql.jdbc.StringUtils;
import com.tw.core.Util.CookieUtil;
import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.Dao.UserDao;

import java.io.*;
import java.net.CookieStore;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.*;

import com.tw.core.service.EmployeeService;
import com.tw.core.service.UserService;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;
    Map<String, Object> data = new HashMap<String, Object>();


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUsers(HttpSession session,HttpServletResponse response) {

        if (session.getAttribute("user") != null) {
            return new ModelAndView("user Information","userList",userService.getUsers());
        } else {
            CookieUtil.saveCookie("previousURL", "/users", response);
            return new ModelAndView("redirect:"+"/login");
        }

    }


    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insertUser(HttpSession session,HttpServletResponse response) throws SQLException {

        if (session.getAttribute("user") != null) {
            return new ModelAndView("insert");
        } else {
            CookieUtil.saveCookie("previousURL", "/users/insert", response);
            return new ModelAndView("redirect:"+"/login");
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insertUser(@RequestParam(value = "name") String userName,
                                   @RequestParam(value = "sex") String userSex,
                                   @RequestParam(value = "age") int userAge,
                                   @RequestParam(value = "password") String userPassword,
                                   @RequestParam(value = "employeeId") int employeeId,
                                   HttpSession session) throws SQLException {

        if (session.getAttribute("user") != null) {

            User user = new User();
            user.setName(userName);
            user.setSex(userSex);
            user.setAge(userAge);
            user.setPassword(userPassword);
//            user.setEmployeeId(employeeId);
            Employee employee = employeeService.getOneEmployee(employeeId);
            user.setEmployee(employee);

            userService.insertUsers(user);
            return new ModelAndView("redirect:" + "/users");
        } else {
            return new ModelAndView("redirect:" + "/login");
        }

    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam(value = "id") int id, HttpSession session) throws SQLException {
        if (session.getAttribute("user") != null) {
            userService.deleteUsers(id);
            return new ModelAndView("redirect:" + "/users");
        } else {
            return new ModelAndView("redirect:" + "/login");
        }
    }


    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public ModelAndView getOneUser(@RequestParam(value = "id") int id, HttpSession session,HttpServletResponse response) throws SQLException {
        if (session.getAttribute("user") != null) {
            return new ModelAndView("modify", "user",userService.getOneUser(id));
        } else {
            return new ModelAndView("redirect:" + "/login");
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ModelAndView updateOneUser(@RequestParam(value = "id") int userId,
                                      @RequestParam(value = "name") String userName,
                                      @RequestParam(value = "sex") String userSex,
                                      @RequestParam(value = "age") int userAge,
                                      @RequestParam(value = "password") String userPassword,
                                      @RequestParam(value = "employeeId") int employeeId,
                                      HttpSession session) {
        if (session.getAttribute("user") != null) {
            User user = new User();
            user.setId(userId);
            user.setName(userName);
            user.setSex(userSex);
            user.setAge(userAge);
            user.setPassword(userPassword);
//            user.setEmployeeId(employeeId);
            Employee employee = employeeService.getOneEmployee(employeeId);
            user.setEmployee(employee);

            userService.UpdateOneUser(user);
            return new ModelAndView("redirect:" + "/users");
        } else {
            return new ModelAndView("redirect:" + "/login");
        }
    }



}