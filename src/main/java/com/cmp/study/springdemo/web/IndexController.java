package com.cmp.study.springdemo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YANLL on 2016/03/30.
 */

@RestController
@RequestMapping
public class IndexController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        System.out.println("login...");
        return "LOGIN";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String hello() {
        System.out.println("main...");
        return "MAIN";
    }

}