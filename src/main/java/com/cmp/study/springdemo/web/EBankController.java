package com.cmp.study.springdemo.web;

import com.yeepay.g3.facade.remit.ebank.dto.QueryRemitBankResultDTO;
import com.yeepay.g3.facade.remit.ebank.dto.RemitBankInfoParamDTO;
import com.yeepay.g3.facade.remit.ebank.dto.RemitBankParamDTO;
import com.yeepay.g3.facade.remit.ebank.dto.RemitBankResultDTO;
import com.yeepay.g3.facade.remit.ebank.service.RemitEBankFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import io.swagger.annotations.Api;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YANLL on 2018/03/14.
 */

@Api(tags = {"网联接口测试"})
@RestController
@RequestMapping(value = "/ebank")
public class EBankController {


    @RequestMapping(value = "/bankRemit", method = RequestMethod.POST)
    public RemitBankResultDTO bankRemit(@RequestBody(required = false) RemitBankParamDTO remitBankParamDTO) {
        String url = "http://10.151.32.27:30020/remit-ebank-hessian/hessian/RemitEBankFacade";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712");
        remitBankParamDTO.setRemitFlowNo("REMIT_FLOW_" + DateUtil.formatDate(new Date(), "yyMMddHHmmss"));
        remitBankParamDTO.setRecAmount(new BigDecimal(66.88));
        remitBankParamDTO.setRemark("imremark");
        if (remitBankParamDTO.getRecAccountNo() == null || remitBankParamDTO.getRecAccountName() == null) {
            remitBankParamDTO.setRecAccountNo("6221558812340000");
            remitBankParamDTO.setRecAccountName("互联网");
        }
        return p.bankRemit(remitBankInfoParamDTO, remitBankParamDTO);

    }

    @RequestMapping(value = "/queryBankRemitSingle", method = RequestMethod.GET)
    public QueryRemitBankResultDTO queryBankRemitSingle(String flowno) {
        String url = "http://10.151.32.27:30020/remit-ebank-hessian/hessian/RemitEBankFacade";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712");
        return p.queryBankRemitSingle(remitBankInfoParamDTO, flowno);

    }

    @RequestMapping(value = "/queryYeePayBalance", method = RequestMethod.GET)
    public BigDecimal queryYeePayBalance() {
        String url = "http://10.151.32.27:30020/remit-ebank-hessian/hessian/RemitEBankFacade";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712");
        return p.queryYeePayBalance(remitBankInfoParamDTO);

    }


}
