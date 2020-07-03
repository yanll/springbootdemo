package tk.techforge.springdemo.modules.user.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.Base;
import tk.techforge.springdemo.modules.user.service.UserService;

/**
 * @author: YANLL
 * @version:
 * @since: 2020/7/3
 */
public class UserMapperTest extends Base {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @Test
    public void test() {
        System.out.println(userMapper.selectCount(null));
        System.out.println(userService.count());
    }

}