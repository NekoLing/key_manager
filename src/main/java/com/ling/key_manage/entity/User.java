package com.ling.key_manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("\"USER\"")
public class User {
    private Long id;
    private String name;
}
