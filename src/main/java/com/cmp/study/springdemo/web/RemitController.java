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


    @RequestMapping(value = "/dz", method = RequestMethod.GET)
    public String dz(String start_time, String end_time) {
        String url = "http://10.151.30.191:8083/remit-hessian/hessian";
        //url = "http://localhost:8083/remit-hessian/hessian";
        StoreEncryptFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, StoreEncryptFacade.class);
        p.remitReTryForUnionpayZ23("remit-hessian", "DZ", start_time, end_time);
        return "OK";
    }

    @RequestMapping(value = "/df", method = RequestMethod.GET)
    public String df(String start_time, String end_time) {
        String url = "http://10.151.30.191:8083/remit-hessian/hessian";
        //url = "http://localhost:8083/remit-hessian/hessian";
        StoreEncryptFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, StoreEncryptFacade.class);
        p.remitReTryForUnionpayZ23("remit-hessian", "DF", start_time, end_time);
        return "OK";
    }


}
