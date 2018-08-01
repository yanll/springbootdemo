package com.cmp.study.springdemo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/https")
public class HttpsController {
    @RequestMapping(value = "/rec", method = RequestMethod.POST)
    public String rec() {
        return "OK";
    }
}
