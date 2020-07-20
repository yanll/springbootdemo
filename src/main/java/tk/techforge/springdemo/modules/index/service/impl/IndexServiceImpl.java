package tk.techforge.springdemo.modules.index.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.techforge.springdemo.modules.index.service.IndexService;
import tk.techforge.springdemo.modules.user.bean.User;
import tk.techforge.springdemo.modules.user.mapper.UserMapper;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    UserMapper userMapper;

    @Cacheable(value = "/index/{id}", key = "#key")
    public User getIndex(String key) {
        return userMapper.selectById(key);
    }

    @Override
    @CachePut(value = "/index/{id}", key = "#key")
    public User updateIndex(String key, String m) {
        User u = userMapper.selectById(key);
        u.setMobile(m);
        userMapper.updateById(u);
        return u;
    }

}
