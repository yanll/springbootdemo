package tk.techforge.springdemo.modules.user.service.impl;

import tk.techforge.springdemo.modules.user.bean.User;
import tk.techforge.springdemo.modules.user.mapper.UserMapper;
import tk.techforge.springdemo.modules.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author YANLL
 * @since 2020-07-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
