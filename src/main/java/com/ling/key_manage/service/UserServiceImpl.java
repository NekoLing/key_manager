package com.ling.key_manage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.key_manage.entity.User;
import com.ling.key_manage.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
