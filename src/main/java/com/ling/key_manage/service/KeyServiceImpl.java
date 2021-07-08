package com.ling.key_manage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.key_manage.entity.Key;
import com.ling.key_manage.mapper.KeyMapper;
import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl extends ServiceImpl<KeyMapper, Key> implements KeyService{

    //根据密钥名获取密钥，如果对应密钥不存在则返回null
    @Override
    public Key getByName(String name) {
        QueryWrapper<Key> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        Key key = getOne(queryWrapper);
        if (key != null)
            return key;
        else
            return null;
    }


}
