package com.cl.crawler.controller;

import com.cl.crawler.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * by cl at 2020/5/8 0008
 */
@Controller
@RequestMapping("/mvc")
public class MvcController {

    @Autowired
    private UserDAO userDAO;
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
