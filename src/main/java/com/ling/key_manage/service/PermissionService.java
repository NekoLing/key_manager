package com.ling.key_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ling.key_manage.entity.Permission;

public interface PermissionService extends IService<Permission> {
    boolean check(Long key_id, Long user_id);
    boolean checkByName(String keyName, String userName);
    String getAllKeyWithUser();
}
