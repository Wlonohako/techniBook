package com.technibook.technibook.api;

import com.technibook.technibook.model.GroupPermission;

import lombok.Data;

@Data
public class AddGroupRequest {
    private Integer userId;
    private GroupPermission permission;
}
