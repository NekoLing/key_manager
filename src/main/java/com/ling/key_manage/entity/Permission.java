package com.ling.key_manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_key")
public class Permission {
    private Long keyId;
    private Long userId;
}
