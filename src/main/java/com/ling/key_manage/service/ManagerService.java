package com.ling.key_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.key_manage.entity.Manager;
public interface ManagerService extends IService<Manager> {
    Manager getByName(String name);
    boolean Login(String name,String psw);
}
