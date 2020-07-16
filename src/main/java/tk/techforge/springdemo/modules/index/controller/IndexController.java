package tk.techforge.springdemo.modules.index.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.springdemo.modules.index.bean.HomeVO;
import tk.techforge.springdemo.modules.index.service.IndexService;

/**
 * Created by YANLL on 2016/03/30.
 */

@RestController
@RequestMapping
@Slf4j
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping(value = "/v")
    public HomeVO v(String k) {
        System.out.println();
        return indexService.getV(k);
    }

    @GetMapping(value = "/get")
    public String getIndex(String k) {
        System.out.println();
        return indexService.getIndex(k);
    }

    @GetMapping(value = "/update")
    public String updateIndex(String k) {
        return indexService.updateIndex(k);
    }

}