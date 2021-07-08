package com.ling.key_manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("manager")
public class Manager {
    private String name;
    private String password;
}
