package com.cmp.study.springdemo.web;

import com.cmp.study.springdemo.service.IIndexService;
import com.cmp.study.springdemo.service.IUserService;
import com.cmp.study.springdemo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by breez on 2016/03/30.
 */

@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    IIndexService indexService;

    @Autowired
    IUserService userService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public UserVO hello() {
        System.out.println("hello...");
        UserVO user = new UserVO();
        user.setName("admin");
        user.setAddress("china");
        indexService.index();
        userService.hello();
        return user;
    }

}