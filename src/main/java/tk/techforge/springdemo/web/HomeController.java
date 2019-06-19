package tk.techforge.springdemo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.springdemo.entity.HomeVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YANLL on 2016/03/30.
 */

@Api(value = "主页API", tags = {"主页", "ALL"})
@RestController
@RequestMapping
@Slf4j
public class HomeController {

    @ApiOperation("主页")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public HomeVO home(HttpServletRequest request) {
        System.out.println(request.getHeader("host"));
        HomeVO homeVO = new HomeVO();
        homeVO.setName("hello");
        return homeVO;
    }
}