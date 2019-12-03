package com.lou.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Biggfish
 */
@Controller
public class ViewController {
    /**
     *
     * @return index页面映射
     */
    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

}

