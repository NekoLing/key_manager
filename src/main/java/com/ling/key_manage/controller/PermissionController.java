package com.ling.key_manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.key_manage.entity.Permission;
import com.ling.key_manage.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("permission")
public class PermissionController {
    final
    PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll() {
        String permissions = service.getAllKeyWithUser();
        return ResponseEntity.ok(permissions);
    }

    @PostMapping("validation")
    public ResponseEntity check(@RequestBody Map<String, String> permission) {
        if (service.checkByName(permission.get("keyName"), permission.get("userName")))
            return ResponseEntity.status(HttpStatus.OK).body("{\"result\":\" 用户有权限 \"}");
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{\"result\":\" 用户无权限 \"}");
    }
    
    @PostMapping
    public ResponseEntity insert(@RequestBody Permission permission){
       boolean result= service.save(permission);
       if(result){
           return ResponseEntity.ok("{\"result\":\" 新建密钥策略成功 \"}");
       }
       else
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\":\" 新建密钥策略失败 \"}");
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Permission permission){
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>(permission);
        boolean result=service.remove(queryWrapper);
        if(result) {
            return ResponseEntity.ok("{\"result\":\" 删除成功 \"}");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"result\":\" 删除失败 \"}");
        }
    }
}
