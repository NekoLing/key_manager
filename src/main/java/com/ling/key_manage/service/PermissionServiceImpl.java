package com.ling.key_manage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.ling.key_manage.entity.Key;
import com.ling.key_manage.entity.Permission;
import com.ling.key_manage.entity.User;
import com.ling.key_manage.mapper.PermissionMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{
    final
    UserService userService;
    final
    KeyService keyService;

    public PermissionServiceImpl(UserService userService, KeyService keyService) {
        this.userService = userService;
        this.keyService = keyService;
    }

    //检查用户是否有对应密钥的权限，有则返回true，没有返回false
    @Override
    public boolean check(Long key_id, Long user_id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("key_id", key_id.longValue()).eq("user_id", user_id.longValue());
        Permission permission = getOne(queryWrapper);

        return permission != null;
    }


    //返回需要结构的Json对象 也就是密钥视角对应有权限的所有用户
    @Override
    public String getAllKeyWithUser() {
        List<User> users;
        List<Permission> permissions;
        QueryWrapper<Permission> queryWrapper;
        KeyWithUser keyWithUser;

        List<KeyWithUser> result = new ArrayList<>();

        //获取所有密钥
        List<Key> keys = keyService.list();
        for (Key key : keys) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("key_id", key.getId());
            //对每个密钥，获取它的所有用户
            permissions = list(queryWrapper);
            users = new ArrayList<>();

            //如果用户集为空，则不显示这个密钥
            if (permissions.isEmpty())
                continue;

            for (Permission permission : permissions) {
                users.add(userService.getById(permission.getUserId().longValue()));
            }

            //包装成对象加入结果列表
            keyWithUser = new KeyWithUser();
            keyWithUser.setKey(key);
            keyWithUser.setUsers(users);
            result.add(keyWithUser);
        }

        String json = new Gson().toJson(result);

        return json;
    }

    @Data
    private class KeyWithUser {
        private Key key;
        private List<User> users;
    }
}
