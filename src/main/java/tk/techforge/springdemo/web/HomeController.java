package tk.techforge.springdemo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tk.techforge.springdemo.entity.HomeVO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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


    @ResponseBody
    @GetMapping(value = "/bg")
    public String bg(HttpServletRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            LinkedHashMap rs = restTemplate.getForObject("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=2", LinkedHashMap.class);
            if (rs == null) return "";
            if (rs.get("images") == null) return "";
            ArrayList imgs = (ArrayList) rs.get("images");
            if (imgs.size() == 0) return "";
            LinkedHashMap img = (LinkedHashMap) imgs.get(0);
            if (img.get("url") == null) return "";
            String url = img.get("url").toString();
            if (url != null && url.length() > 0) return "https://cn.bing.com" + img.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException("获取BingCN图片失败！");
        }
        return "";
    }
}