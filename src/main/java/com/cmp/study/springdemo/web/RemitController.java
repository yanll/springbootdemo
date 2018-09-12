package com.cmp.study.springdemo.web;

import com.yeepay.g3.facade.remit.service.StoreEncryptFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YANLL on 2018/03/14.
 */

@Api(tags = {"REMIT接口测试"})
@RestController
@RequestMapping(value = "/remit")
public class RemitController {


    @RequestMapping(value = "/remitReTryForUnionpayZ23", method = RequestMethod.GET)
    public String remitReTryForUnionpayZ23(String channel_type, String start_time, String end_time) {
        String url = "";
        url = "http://localhost:8014/remit-hessian/hessian";
        StoreEncryptFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, StoreEncryptFacade.class);
        p.remitReTryForUnionpayZ23("remit-hessian", channel_type, start_time, end_time);
        return "OK";
    }


}
