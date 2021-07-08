package com.ling.key_manage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.key_manage.entity.Key;
import com.ling.key_manage.entity.Manager;
import com.ling.key_manage.mapper.ManagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.SelectTag;

@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper,Manager> implements ManagerService {


    @Override
    //查找管理员
    public Manager getByName(String name) {
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        Manager manager = getOne(queryWrapper);
        if (manager == null) return null;
        return manager;

    }

    @Override
    //验证登录
    public boolean Login(String name, String psw) {

        if (getByName(name) == null)
            return false;//验证管理员是否存在

        if (getByName(name).getPassword().equals(psw))//验证密码

            return true;
        else
            return false;

    }
}