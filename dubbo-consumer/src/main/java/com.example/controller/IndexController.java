package com.example.controller;

import com.example.service.OrderService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response){
        System.out.println("-------------->进入index页面");
        String id = request.getParameter("id");
        System.out.println("-------------->id = " + id);
        String userView = userService.getDetail(id);
        String orderView = orderService.getDetail(id);

        request.setAttribute("userView", userView);
        request.setAttribute("orderView", orderView);
        return "index";
    }
}
