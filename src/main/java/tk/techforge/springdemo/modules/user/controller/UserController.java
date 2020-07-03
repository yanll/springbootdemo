package tk.techforge.springdemo.modules.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.techforge.springdemo.modules.index.bean.ResponseMsg;
import tk.techforge.springdemo.modules.user.bean.User;
import tk.techforge.springdemo.modules.user.service.UserService;

import java.io.Serializable;

;
;

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
    public ResponseMsg page(String id) {
        return null;
    }

}

