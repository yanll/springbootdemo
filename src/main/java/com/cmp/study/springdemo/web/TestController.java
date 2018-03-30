package com.cmp.study.springdemo.web;

import com.yeepay.g3.facade.bankinfo.dto.AccInfoDTO;
import com.yeepay.g3.facade.bankinfo.service.BankInfoQueryFacade;
import com.yeepay.g3.facade.bankinfo.service.EnvFacade;
import com.yeepay.g3.facade.remit.dto.BankCardParamsDTO;
import com.yeepay.g3.facade.remit.dto.RemitRequestParamsDTO;
import com.yeepay.g3.facade.remit.enumtype.AccountTypeEnum;
import com.yeepay.g3.facade.remit.enumtype.CurrencyTypeEnum;
import com.yeepay.g3.facade.remit.enumtype.RemitFailModeEnum;
import com.yeepay.g3.facade.remit.service.RemitScheduleFacade;
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
import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "/autoCreateUrgentRemitBatches", method = RequestMethod.GET)
    public String autoCreateUrgentRemitBatches() {
        //String url = "http://127.0.0.1:8067/remit-hessian/hessian";
        RemitScheduleFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, RemitScheduleFacade.class);
        List<String> list_ = new ArrayList<>();
        list_.add("2GWTJS_1519799061292");
        list_.add("2GWTJS_1519799061885");
        list_.add("2GWTJS_1519805406924");
        list_.add("2GWTJS_1519805408172");
        list_.add("2GWTJS_1519805680652");
        //p.autoCreateUrgentRemitBatches(list);
        p.autoCreateRealTimeRemitBatch();
        return null;
    }

    @RequestMapping(value = "/req", method = RequestMethod.GET)
    public String req() {
        String url = "http://127.0.0.1:8067/remit-hessian/hessian";
        RemitTransactionFacade r = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitTransactionFacade.class);
        RemitRequestParamsDTO remitRequestParamsDTO = new RemitRequestParamsDTO();
        remitRequestParamsDTO.setRemitRequestNo(System.currentTimeMillis() + "");//打款请求号  remitRequestNo
        remitRequestParamsDTO.setAccountType(AccountTypeEnum.TO_PRIVATE);// 账户类型  accountType
        remitRequestParamsDTO.setCurrencyType(CurrencyTypeEnum.RMB);//币种  currencyType
        remitRequestParamsDTO.setOwnerSys("2GWTJS");
        remitRequestParamsDTO.setTradeInitiator("TRANSFEXT");
        remitRequestParamsDTO.setAmount(new BigDecimal("10000"));//请求金额  amount
        remitRequestParamsDTO.setUrgent(true);
        remitRequestParamsDTO.setRemitType("");
        remitRequestParamsDTO.setCustomerNo("20040008157");
        remitRequestParamsDTO.setAutoRemit(true);
        remitRequestParamsDTO.setAppointmentChannel("JSB_SECOND_PRI");// JSB_SECOND_PRI 江苏秒到   EGBANK_CUP_PRI 恒丰
        remitRequestParamsDTO.setFailMode(RemitFailModeEnum.REMIT_FIRST_TRY);
        remitRequestParamsDTO.setNotifyAddress("redirect:http://10.151.32.27:30118/accounting-boss/remit/receiveRemitResult");
        BankCardParamsDTO bankCardParamsDTO = new BankCardParamsDTO();
        bankCardParamsDTO.setOpenAccountName("YAN");//开户人  openAccountName
        bankCardParamsDTO.setCardNo("6225990944345555");//收款卡号  cardNo
        bankCardParamsDTO.setBankName("工商银行");//银行名称  bankName
        bankCardParamsDTO.setBankNo("ABC");//银行编号  bankNo
        bankCardParamsDTO.setProvince("01");
        bankCardParamsDTO.setCity("01");
        remitRequestParamsDTO.setBankCard(bankCardParamsDTO);
        r.launchRemitRequest(remitRequestParamsDTO);
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

    //天津农村商业银行股份有限公司蓟县侯家营支行 402110013752
    @RequestMapping(value = "/queryBranchBank/{bank_code}/{province_code}/{city_code}", method = RequestMethod.GET)
    public Object queryBranchBank(@PathVariable("bank_code") String bank_code, @PathVariable("province_code") String province_code, @PathVariable("city_code") String city_code) {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, BankInfoQueryFacade.class);
        return p.queryBranchBank(bank_code, province_code, city_code);
    }

    @ApiOperation("查询所有总行信息")
    @RequestMapping(value = "/queryAllHeadBank", method = RequestMethod.GET)
    public Object queryAllHeadBank() {
        BankInfoQueryFacade p = RemoteServiceFactory.getService(BankInfoQueryFacade.class);
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
        dto.setCity("328");
        dto.setProvince("328");
        dto.setBankNo("328");
        dto.setBranchBankName("328");
        dto.setAccNo("328");
        return p.studyAccInfoOne(dto);
    }

}
