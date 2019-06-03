package tk.techforge.springdemo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.springdemo.entity.HomeVO;
import tk.techforge.springdemo.entity.ResponseMsg;

/**
 * Created by YANLL on 2016/03/30.
 */

@Api(value = "主页API", tags = {"主页","ALL"})
@RestController
@RequestMapping
@Slf4j
public class HomeController {

    @ApiOperation("SSS")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public HomeVO home() {
        HomeVO homeVO = new HomeVO();
        homeVO.setName("hello");
        return homeVO;
    }

    @ApiOperation("SSS")
    @RequestMapping(value = "/homeT", method = RequestMethod.GET)
    public ResponseMsg<HomeVO> homeT() {
        HomeVO homeVO = new HomeVO();
        homeVO.setName("hello,world!");
        return ResponseMsg.<HomeVO>instance(homeVO);
    }

    @ApiOperation("SSS")
    @RequestMapping(value = "/home_", method = RequestMethod.GET)
    public ResponseMsg home_() {
        HomeVO homeVO = new HomeVO();
        homeVO.setName("hello,boy!");
        return ResponseMsg.instance(homeVO);
    }
}