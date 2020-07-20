package tk.techforge.springdemo.modules.index.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.techforge.springdemo.commons.ResponseMsg;
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

    @GetMapping(value = "/get")
    public ResponseMsg getIndex(String k) {
        return ResponseMsg.data(indexService.getIndex(k));
    }

    @GetMapping(value = "/update")
    public ResponseMsg updateIndex(String k) {
        return ResponseMsg.data(indexService.updateIndex(k));
    }

}