package tk.techforge.springdemo.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.techforge.springdemo.commons.PaginationUtil;
import tk.techforge.springdemo.commons.ParamValidator;
import tk.techforge.springdemo.commons.ResponseMsg;
import tk.techforge.springdemo.modules.user.bean.User;
import tk.techforge.springdemo.modules.user.service.UserService;

import java.io.Serializable;



/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author YANLL
 * @since 2020-07-03
 */
@RestController
@RequestMapping("/user/user")
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseMsg add(@RequestBody User user) {
        boolean result = userService.save(user);
        return new ResponseMsg(HttpStatus.OK, "添加成功！", result);
    }

    @PutMapping("/update")
    public ResponseMsg modify(@RequestBody User user) {
        boolean result = userService.save(user);
        return new ResponseMsg(HttpStatus.OK, "更新成功！", result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMsg delete(@PathVariable("id") Serializable id) {
        boolean result = userService.removeById(id);
        return new ResponseMsg(HttpStatus.OK, "删除成功！", result);
    }

    @GetMapping("/get")
    public ResponseMsg get(@PathVariable("id") Serializable id) {
        User user = userService.getById(id);
        return new ResponseMsg(HttpStatus.OK, user);
    }

    @GetMapping("/page")
    public ResponseMsg page(Long page, Long limit) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();


        IPage<User> result = userService.page(PaginationUtil.toPage(page, limit), wrapper);
        return new ResponseMsg(HttpStatus.OK, result);
    }

    @GetMapping("/sss")
    public Object page(String name) {
//        ParamValidator<User> dog = user -> {
//            System.out.println("-------------" + user.getEmail());
//            return new ResponseMsg(HttpStatus.OK, "sssssssssssssss");
//        };
//        User u = new User();
//        u.setEmail("sssssssss");
//        dog.validate(u);

        return "sss";
    }

}

