package com.cmp.study.springdemo.web;

import com.yeepay.g3.facade.bankinfo.dto.AccInfoDTO;
import com.yeepay.g3.facade.bankinfo.service.BankInfoQueryFacade;
import com.yeepay.g3.facade.bankinfo.service.EnvFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YANLL on 2018/03/14.
 */

@Api(tags = {"银行信息子系统接口测试"})
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @RequestMapping(value = "/printEnv", method = RequestMethod.GET)
    public String printEnv() {
        EnvFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, EnvFacade.class);
        p.printEnv();
        return null;
    }

    @RequestMapping(value = "/clearrediscache", method = RequestMethod.GET)
    public String clearrediscache() {
        EnvFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, EnvFacade.class);
        p.clearrediscache();
        return null;
    }

    @ApiOperation("REDIS缓存测试")
    @RequestMapping(value = "/redis_value/{key}", method = RequestMethod.GET)
    public Object getRedisValue(@PathVariable("key") String key) {
        EnvFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, EnvFacade.class);
        return p.getRedisValue(key);
    }


    /*
    @RequestMapping(value = "/autoCreateUrgentRemitBatches", method = RequestMethod.GET)
    public String autoCreateUrgentRemitBatches() {
        String url = "http://127.0.0.1:8067/remit-hessian/hessian";
        RemitScheduleFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitScheduleFacade.class);
        List<String> list = new ArrayList<>();
        list.add("2GWTJS_1519799061292");
        list.add("2GWTJS_1519799061885");
        list.add("2GWTJS_1519805406924");
        list.add("2GWTJS_1519805408172");
        list.add("2GWTJS_1519805680652");
        p.autoCreateUrgentRemitBatches(list);
        return null;
    }
    */

    @RequestMapping(value = "/queryHeadBankByCode/{bank_code}", method = RequestMethod.GET)
    public Object queryHeadBankByCode(@PathVariable("bank_code") String bank_code) {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        return p.queryHeadBankByCode(bank_code);
    }

    @RequestMapping(value = "/queryBranchBankByCode/{branch_code}", method = RequestMethod.GET)
    public Object queryBranchBankByCode(@PathVariable("branch_code") String branch_code) {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        return p.queryBranchBankByCode(branch_code);
    }

    //天津农村商业银行股份有限公司蓟县侯家营支行 402110013752
    @RequestMapping(value = "/queryBranchBank/{bank_code}/{province_code}/{city_code}", method = RequestMethod.GET)
    public Object queryBranchBank(@PathVariable("bank_code") String bank_code, @PathVariable("province_code") String province_code, @PathVariable("city_code") String city_code) {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        return p.queryBranchBank(bank_code, province_code, city_code);
    }

    @ApiOperation("查询所有总行信息")
    @RequestMapping(value = "/queryAllHeadBank", method = RequestMethod.GET)
    public Object queryAllHeadBank() {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        return p.queryAllHeadBank();
    }

    @RequestMapping(value = "/queryAllProvince", method = RequestMethod.GET)
    public Object queryAllProvince() {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        return p.queryAllProvince();
    }

    @ApiOperation("根据省份编码查询城市列表信息")
    @RequestMapping(value = "/queryCity/{province_code}", method = RequestMethod.GET)
    public Object queryCity(@ApiParam("省份代码") @PathVariable("province_code") String province_code) {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        return p.queryCity(province_code);
    }

    @RequestMapping(value = "/studyAccInfoOne", method = RequestMethod.GET)
    public Object studyAccInfoOne() {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        AccInfoDTO dto = new AccInfoDTO();
        dto.setCity("01");
        dto.setProvince("01");
        dto.setBankNo("ABC");
        dto.setBranchBankName("d");
        dto.setAccNo("150801117087");
        return p.studyAccInfoOne(dto);
    }

}
