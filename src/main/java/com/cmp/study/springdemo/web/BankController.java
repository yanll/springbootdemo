package com.cmp.study.springdemo.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YANLL on 2018/03/14.
 */

@Api(tags = {"BANK接口测试"})
@RestController
@RequestMapping(value = "/bank")
public class BankController {


}
