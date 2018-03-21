package com.cmp.study.springdemo.web;

import com.yeepay.g3.facade.bankinfo.dto.AccInfoDTO;
import com.yeepay.g3.facade.bankinfo.service.AccInfoMgrFacade;
import com.yeepay.g3.facade.bankinfo.service.BankInfoQueryFacade;
import com.yeepay.g3.facade.bankinfo.service.EnvFacade;
import com.yeepay.g3.facade.remit.dto.BankCardParamsDTO;
import com.yeepay.g3.facade.remit.dto.RemitRequestParamsDTO;
import com.yeepay.g3.facade.remit.enumtype.AccountTypeEnum;
import com.yeepay.g3.facade.remit.enumtype.CurrencyTypeEnum;
import com.yeepay.g3.facade.remit.enumtype.RemitFailModeEnum;
import com.yeepay.g3.facade.remit.service.RemitTransactionFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by YANLL on 2018/03/14.
 */

@Api(tags = {"银行信息子系统接口测试"})
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @RequestMapping(value = "/launchRemitRequest", method = RequestMethod.GET)
    public String launchRemitRequest() {
        String url = "http://10.151.31.60:8067/remit-hessian/hessian";
        RemitTransactionFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitTransactionFacade.class);
        RemitRequestParamsDTO remitRequestParamsDTO = new RemitRequestParamsDTO();
        remitRequestParamsDTO.setRemitRequestNo(System.currentTimeMillis() + "");
        remitRequestParamsDTO.setAccountType(AccountTypeEnum.TO_PRIVATE);
        remitRequestParamsDTO.setCurrencyType(CurrencyTypeEnum.RMB);
        remitRequestParamsDTO.setOwnerSys("2GWTJS");
        remitRequestParamsDTO.setTradeInitiator("WITHDRAW");
        remitRequestParamsDTO.setAmount(new BigDecimal("0.03"));
        remitRequestParamsDTO.setUrgent(false);
        remitRequestParamsDTO.setFailMode(RemitFailModeEnum.REMIT_FIRST_TRY);
        remitRequestParamsDTO.setAutoRemit(false);
        remitRequestParamsDTO.setNotifyAddress("REDIRECT:HTTP:9999");
        remitRequestParamsDTO.setCustomerNo("20040008197");
        remitRequestParamsDTO.setBankRemark("备注信息");
        BankCardParamsDTO bankCardParamsDTO = new BankCardParamsDTO();
        bankCardParamsDTO.setOpenAccountName("商户1111111");
        bankCardParamsDTO.setCardNo("6216261000000000000");
        bankCardParamsDTO.setBankName("广发银行");
        bankCardParamsDTO.setBankNo("GDB");
        bankCardParamsDTO.setProvince("01");
        bankCardParamsDTO.setCity("01");
        remitRequestParamsDTO.setBankCard(bankCardParamsDTO);
        p.launchRemitRequest(remitRequestParamsDTO);
        return null;
    }

    @RequestMapping(value = "/printEnv/{clearrediscache}/{pattern}", method = RequestMethod.GET)
    public String printEnv(@PathVariable("clearrediscache") String clearrediscache, @PathVariable("pattern") String pattern) {
        AccInfoMgrFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, AccInfoMgrFacade.class);
        p.printEnv(clearrediscache, pattern);
        return null;
    }

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

    @ApiOperation("REDIS缓存测试")
    @RequestMapping(value = "/redis_value/{key}", method = RequestMethod.GET)
    public Object getRedisValue(@PathVariable("key") String key) {
        EnvFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, EnvFacade.class);
        return p.getRedisValue(key);
    }

}
