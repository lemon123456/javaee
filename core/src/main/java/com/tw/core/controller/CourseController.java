package com.tw.core.controller;

import com.tw.core.entity.Course;
import com.tw.core.service.CourseService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCourses() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("course");
        modelAndView.addObject("courseList", courseService.getCourses());
        return modelAndView;

    }


    @RequestMapping(method = RequestMethod.POST)
    public void insertCourse(@RequestParam(value = "courseName") String courseName,
                             @RequestParam(value = "description") String description) throws SQLException {

        Course course = new Course(courseName, description);
        courseService.insertCourse(course);
    }

//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    public ModelAndView insertCourse(@RequestParam(value = "courseName") String courseName,
//                                   @RequestParam(value = "description") String description,
//                                   HttpSession session) throws SQLException {
//
//        if (session.getAttribute("user") != null) {
//
//            Course course = new Course(courseName,description);
//            courseService.insertCourse(course);
//
//            return new ModelAndView("redirect:" + "/course");
//        } else {
//            return new ModelAndView("redirect:" + "/login");
//        }
//    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }


//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteCourse(@PathVariable("id") String id,HttpSession session) throws SQLException {
//        if (session.getAttribute("user") != null) {
//            courseService.deleteCourse(Integer.parseInt(id));
//            return new ModelAndView("redirect:" + "/course");
//        } else {
//            return new ModelAndView("redirect:" + "/login");
//        }
//    }

//    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
//    public ModelAndView getOneCourse(@PathVariable("id") String id, HttpSession session) throws SQLException {
//
//        if (session.getAttribute("user") != null) {
//            return new ModelAndView("modifyCourse", "course", courseService.getOneCourse(Integer.parseInt(id)));
//        } else {
//            return new ModelAndView("redirect:" + "/login");
//        }
//    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public void updateOneCourse(@RequestParam(value = "id") int id,
                                @RequestParam(value = "courseName") String courseName,
                                @RequestParam(value = "description") String description,
                                HttpServletResponse response) {

        Course course = new Course(id,courseName, description);
        courseService.updateOneCourse(course);
        course = courseService.getOneCourse(id);

        try{
            JSONObject courseJson = new JSONObject("{'id':'"+course.getId()+
                    "','courseName':'" + course.getCourseName() +
                    "','description':'" + course.getDescription()+"'}");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(courseJson.toString());
        }catch (JSONException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
