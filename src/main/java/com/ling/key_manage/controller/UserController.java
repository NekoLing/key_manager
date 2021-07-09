package com.ling.key_manage.controller;

import com.ling.key_manage.entity.User;
import com.ling.key_manage.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public ResponseEntity getAllUser() {
        List<User> users = userService.list();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity getUserById(@RequestBody User paramUser) {
        long id = paramUser.getId();
        User user = userService.getById(id);
        if (user != null)
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\":\" 未找到指定用户 \"}");
    }

    @PostMapping("user")
    public ResponseEntity insert(@RequestBody User paramUser){
        String userName = paramUser.getName();
        User user = new User();
        user.setName(userName);
        boolean result = userService.save(user);
        if(result) {
            return ResponseEntity.ok("{\"result\":\" 添加用户成功 \"}");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\":\" 添加用户失败 \"}");
        }
    }

    @DeleteMapping("user")
    public ResponseEntity deleteById(@RequestBody User paramUser){
        long id = paramUser.getId();
        boolean result = userService.removeById(id);
        if(result) {
            return ResponseEntity.ok("{\"result\":\" 删除成功 \"}");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\":\" 删除失败 \"}");
        }
    }

    @PutMapping("user")
    public ResponseEntity updateName(@RequestBody User paramUser){
        long id = paramUser.getId();
        boolean result = userService.updateById(paramUser);
        if(result) {
            return ResponseEntity.ok("{\"result\":\" 修改成功 \"}");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\":\" 修改失败 \"}");
        }
    }
}
