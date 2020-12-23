package com.zt.javastudy.work;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author zhengtao
 * @description 测试fastjson是否支持转list对象
 * @date 2020/12/18
 */
@Data
public class TestFastJson {
    @JSONField(name = "coupon_detail")
    private List<TestList> coupon_detail ;
}
