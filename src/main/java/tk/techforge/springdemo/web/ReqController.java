package tk.techforge.springdemo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.springdemo.entity.HomeVO;
import tk.techforge.springdemo.entity.ResponseMsg;

/**
 * Created by YANLL on 2016/03/30.
 */

@Api(value = "请求参数API", tags = {"请求参数", "ALL"})
@RestController
@RequestMapping("/req")
@Slf4j
public class ReqController {

    @ApiOperation(value = "bbbbbbbbbb", notes = "aaaaaa", response = String.class)
    @GetMapping(value = "/situation1")
    public String situation1(String name) {
        return "OK";
    }

    @ApiOperation(value = "111111111111", notes = "222222222222", response = HomeVO.class)
    @GetMapping(value = "/situation2")
    public HomeVO situation2(String name) {
        return new HomeVO(100L, "ok", "http://");
    }

    @ApiOperation(value = "111111111111", notes = "222222222222", response = ResponseMsg.class)
    @GetMapping(value = "/situation3")
    public HomeVO situation3(String name) {
        return new HomeVO(100L, "ok", "http://");
    }

    @ApiOperation(value = "111111111111", notes = "222222222222", response = ResponseMsg.class)
    @GetMapping(value = "/situation4")
    public HomeVO situation4(String name) {
        return new HomeVO(100L, "ok", "http://");
    }


}