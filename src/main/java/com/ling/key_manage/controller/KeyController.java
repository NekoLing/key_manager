package com.ling.key_manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.JsonObject;
import com.ling.key_manage.entity.Key;
import com.ling.key_manage.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bc.sm4.SM4Key;

import java.util.List;

@RestController
@RequestMapping("keys")
public class KeyController {

    //作用和@Autowired一样 现在官方更推荐下面的方式
    final
    KeyService keyService;

    public KeyController(KeyService keyService) {
        this.keyService = keyService;
    }

    @GetMapping
    public ResponseEntity getAllKeys() {
        List<Key> keys = keyService.list();
        return ResponseEntity.ok(keys);
    }

    @PostMapping
    public ResponseEntity getKeyByName(@RequestBody Key paramKey) {
        String keyName = paramKey.getName();
        Key key = keyService.getByName(keyName);
        if (key != null)
            return ResponseEntity.ok(key);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到指定密钥");
    }

    @PostMapping("key")
    public ResponseEntity insert(@RequestBody Key paramKey){
        String keyName = paramKey.getName();
        Key key = new Key();
        key.setName(keyName);
        try {
            String keyData = SM4Key.generateKey();
            key.setData(keyData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean result = keyService.save(key);
        if(result == true) {
            return ResponseEntity.ok("新建密钥成功");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("新建密钥失败");
        }
    }

    @DeleteMapping("key")
    public ResponseEntity deleteById(@RequestBody Key paramKey){
        long id = paramKey.getId();
        boolean result = keyService.removeById(id);
        if(result == true) {
            return ResponseEntity.ok("删除成功");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除失败");
        }
    }

    @PutMapping("key")
    public ResponseEntity updateName(@RequestBody Key paramKey){
        long id = paramKey.getId();
        Key key = keyService.getById(id);
        paramKey.setData(key.getData());
        boolean result = keyService.updateById(paramKey);
        if(result == true) {
            return ResponseEntity.ok("修改成功");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("修改失败");
        }
    }



}
