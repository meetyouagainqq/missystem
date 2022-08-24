package com.javasm.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String headImg;
    private String remark;
    private Integer roleId;
    //时间值固定
    private String regTime;
    private String loginTime;
    private Integer isvalid;
    //创建人的id
    private Integer createUid;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String headImg, String remark, Integer roleId, String regTime, Integer isvalid, Integer createUid) {
        this.username = username;
        this.headImg = headImg;
        this.remark = remark;
        this.roleId = roleId;
        this.regTime = regTime;
        this.isvalid = isvalid;
        this.createUid = createUid;
    }
}
