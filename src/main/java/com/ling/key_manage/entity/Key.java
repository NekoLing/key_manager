package com.ling.key_manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mykeys")
public class Key {
    private Long id;
    private String name;
    private String data;
}
