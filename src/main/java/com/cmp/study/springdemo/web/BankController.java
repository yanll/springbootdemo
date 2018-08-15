package com.cmp.study.springdemo.web;

import com.yeepay.g3.facade.remit.bank.service.GuangdaUnionpayFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YANLL on 2018/03/14.
 */

@Api(tags = {"BANK接口测试"})
@RestController
@RequestMapping(value = "/bank")
public class BankController {


    @RequestMapping(value = "/downloadDZDByBankInfoId", method = RequestMethod.POST)
    public void downloadDZDByBankInfoIdAndDate(String date) {
        String url = "http://10.151.31.59:8055/remit-bank-hessian/hessian";
        GuangdaUnionpayFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, GuangdaUnionpayFacade.class);
        p.downloadDZDByBankInfoIdAndDate("BANK20170712DF", date);
    }


}
