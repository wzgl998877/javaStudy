package com.zt.javastudy.mybatis.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhengtao
 */
@Data
public class TUser {
    private String userId;

    private String identityId;

    private String name;

    private String gender;

    private Date birthdate;

    private String mobile;

    private String source;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
    /**
     * 认证状态：0-已注册，1-认证通过，2-认证不通过，3-已冻结，4-已注销，5-已删除
     */
    private String authStatus;
}