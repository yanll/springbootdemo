package com.cmp.study.springdemo.web;

import com.yeepay.g3.facade.remit.ebank.dto.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YANLL on 2018/03/14.
 */

@Api(tags = {"网联接口测试"})
@RestController
@RequestMapping(value = "/ebank")
public class EBankController {


    @RequestMapping(value = "/batchBankRemit", method = RequestMethod.POST)
    public BatchRemitBankResultDTO batchBankRemit(@RequestBody(required = false) RemitBankParamDTO remitBankParamDTO) {
        String url = "http://10.151.32.27:30020/remit-ebank-hessian/hessian/RemitEBankFacade";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712DF");
        remitBankParamDTO.setRemitFlowNo("REMIT_FLOW_" + DateUtil.formatDate(new Date(), "yyMMddHHmmss"));
        remitBankParamDTO.setRecAmount(new BigDecimal(66.88));
        remitBankParamDTO.setRemark("imremark");
        remitBankParamDTO.setRecProvinceCode("01");
        remitBankParamDTO.setRecCityCode("01");
        remitBankParamDTO.setBankNo("GD");
        remitBankParamDTO.setBankName("光大");
        remitBankParamDTO.setRecBankCode("GD001");
        remitBankParamDTO.setRecBankName("光大001");
        remitBankParamDTO.setPostscript("Postscript");
        remitBankParamDTO.setRecBankCode("RC");
        remitBankParamDTO.setRecBankCode("RCNAME");
        if (remitBankParamDTO.getRecAccountNo() == null || remitBankParamDTO.getRecAccountName() == null) {
            remitBankParamDTO.setRecAccountNo("6221558812340000");
            remitBankParamDTO.setRecAccountName("互联网");
        }
        BatchRemitBankParamDTO batchRemitBankParamDTO = new BatchRemitBankParamDTO();
        batchRemitBankParamDTO.setBatchNo("B" + remitBankParamDTO.getRemitFlowNo());
        List<RemitBankParamDTO> list = new ArrayList<>();
        list.add(remitBankParamDTO);
        batchRemitBankParamDTO.setRemitBankParamDTOList(list);
        return p.batchBankRemit(remitBankInfoParamDTO, batchRemitBankParamDTO);

    }

    @RequestMapping(value = "/queryBankRemitSingle", method = RequestMethod.GET)
    public QueryRemitBankResultDTO queryBankRemitSingle(String flowno) {
        String url = "http://10.151.32.27:30020/remit-ebank-hessian/hessian/RemitEBankFacade";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712DF");
        return p.queryBankRemitSingle(remitBankInfoParamDTO, flowno);
    }

    @RequestMapping(value = "/queryBankRemitBatch", method = RequestMethod.GET)
    public BatchQueryRemitBankResultDTO queryBankRemitBatch(String batchno) {
        String url = "http://10.151.32.27:30020/remit-ebank-hessian/hessian/RemitEBankFacade";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712DF");
        return p.queryBankRemitBatch(remitBankInfoParamDTO, batchno);
    }

    @RequestMapping(value = "/queryYeePayBalance", method = RequestMethod.GET)
    public BigDecimal queryYeePayBalance() {
        String url = "http://10.151.32.44:30020/remit-ebank-hessian/hessian";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712DF");
        return p.queryYeePayBalance(remitBankInfoParamDTO);
    }

    @RequestMapping(value = "/downloadAccountFile", method = RequestMethod.GET)
    public DownloadAccountFileResponseDTO downloadAccountFile() {
        String url = "http://10.151.32.27:30020/remit-ebank-hessian/hessian/RemitEBankFacade";
        url = "http://localhost:30020/remit-ebank-hessian/hessian";
        RemitEBankFacade p = RemoteServiceFactory.getService(url, RemotingProtocol.HESSIAN, RemitEBankFacade.class);
        RemitBankInfoParamDTO remitBankInfoParamDTO = new RemitBankInfoParamDTO();
        remitBankInfoParamDTO.setRemitBankId("BANK20180712DF");
        DownloadAccountFileRequestDTO downloadAccountFileRequestDTO = new DownloadAccountFileRequestDTO();
        return p.downloadAccountFile(remitBankInfoParamDTO, downloadAccountFileRequestDTO);
    }


}
