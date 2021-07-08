package com.ling.key_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.key_manage.entity.Key;

public interface KeyService extends IService<Key> {
    Key getByName(String name);
    Key creatKey(String keyName);

}
