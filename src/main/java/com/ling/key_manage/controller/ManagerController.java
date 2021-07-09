package com.ling.key_manage.controller;


import com.ling.key_manage.entity.Manager;
import com.ling.key_manage.service.ManagerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("manager")
public class ManagerController {

    final ManagerService managerService;

    public ManagerController(ManagerService managerService){this.managerService=managerService;}

   @PostMapping()
    public ResponseEntity checkLogin(@RequestBody Manager manager){

       if (managerService.Login(manager.getName(), manager.getPassword()))
           return ResponseEntity.ok("{\"result\":\" 登录成功 \"}");
       else
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{\"result\":\" 登录失败失败 \"}");

   }

}
