package com.zt.javastudy.mybatis.mapper;

import com.zt.javastudy.mybatis.entity.TUser;


/**
 * @author zhengtao
 */
public interface TUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
    /**
     * 根据手机号码查询
     * @param mobile
     * @return
     */
    TUser selectByMobile(String mobile);
}