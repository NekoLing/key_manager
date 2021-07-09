package com.ling.key_manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.ling.key_manage.entity.Key;
import com.ling.key_manage.entity.Permission;
import com.ling.key_manage.entity.User;
import com.ling.key_manage.mapper.KeyMapper;
import com.ling.key_manage.service.KeyService;
import com.ling.key_manage.service.PermissionServiceImpl;
import com.ling.key_manage.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bc.sm4.SM4Key;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class KeyManageApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user;
        for (int i = 1; i < 10; i++) {
            user = new User();
            user.setName("用户" + i);
            userService.save(user);
        }
    }
}
