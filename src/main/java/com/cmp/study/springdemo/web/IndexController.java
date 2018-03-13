package com.cmp.study.springdemo.web;

import com.cmp.study.springdemo.service.IIndexService;
import com.cmp.study.springdemo.service.IUserService;
import com.cmp.study.springdemo.vo.UserVO;
import com.yeepay.g3.facade.bankinfo.dto.AccInfoDTO;
import com.yeepay.g3.facade.bankinfo.dto.HeadBankDTO;
import com.yeepay.g3.facade.bankinfo.service.AccInfoMgrFacade;
import com.yeepay.g3.facade.bankinfo.service.BankInfoQueryFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @RequestMapping(value = "/test")
    public Object test() {
        //ConfigParam<?> configParam = ConfigurationUtils.getConfigParam("config_type_text_resources", "bankinfo-hessian-redis-switch");
        //Object obj = configParam.getValue();
        //if (true) return obj;
        //String url = "http://127.0.0.1:8086/bankinfo-hessian/hessian";
        if (true) {
            AccInfoMgrFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, AccInfoMgrFacade.class);
            p.printEnv();
        }
        if (false) {
            BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
            if (true) return p.queryAllProvince();
            AccInfoDTO acc = new AccInfoDTO();
            acc.setAccNo("622599033244222");
            acc.setBranchBankNo("303791000193");
            acc.setBranchBankName("中国光大银行西安新城支行");
            acc = p.studyAccInfoOne(acc);
            return acc;
        }
        if (true) {
            BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
            HeadBankDTO headbank = p.queryHeadBankByCode("CCB");
            return headbank;
        }
        return null;
    }


    @RequestMapping(value = "/hello")
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