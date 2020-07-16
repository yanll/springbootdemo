package tk.techforge.springdemo.modules.index.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.techforge.springdemo.modules.index.bean.HomeVO;
import tk.techforge.springdemo.modules.index.service.IndexService;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    @Cacheable(value = "/index/", key = "#key")
    public HomeVO getV(String key) {
        HomeVO v = new HomeVO();
        v.setId(10000L);
        v.setName("admin");
        return v;
    }

    @Override
    @Cacheable(value = "/index/", key = "#key")
    public String getIndex(String key) {
        return "getIndex";
    }

    @Override
    @CachePut(value = "/index/", key = "#key")
    public String updateIndex(String key) {
        return "updateIndex";
    }

}
