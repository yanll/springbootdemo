package tk.techforge.springdemo.modules.index.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.springdemo.modules.index.bean.HomeVO;

/**
 * @author: YANLL
 * @version:
 * @since: 2019/05/31
 */
@Api(value = "ApiOperation注解API", tags = {"ApiOperation注解", "ALL"})
@RestController
@RequestMapping("/api_operation")
@Slf4j
public class ApiOperationController {

    @ApiOperation(value = "测试注解：notes", notes = "此接口用于获取元数据信息\n1、已提测\n2、描述信息")
    @GetMapping(value = "/notes")
    public String notes() {
        return "OK";
    }

    @ApiOperation(value = "测试注解：tags", tags = {"tag"})
    @GetMapping(value = "/tags")
    public String tags() {
        return "OK";
    }

    @ApiOperation(value = "测试注解：response", response = HomeVO.class)
    @GetMapping(value = "/response")
    public HomeVO response() {
        return new HomeVO(100L, "hello", "");
    }

    @ApiOperation(value = "测试注解：response_force", response = HomeVO.class)
    @GetMapping(value = "/response_force")
    public String response_force() {
        return "OK";
    }

    @ApiOperation(value = "测试注解：其它",
            responseContainer = "responseContainer",
            responseReference = "responseReference",
            httpMethod = "GET",
            nickname = "nickname",
            produces = "produces",
            consumes = "consumes",
            protocols = "protocols"
    )
    @GetMapping(value = "/others")
    public String others() {
        return "OK";
    }
}
