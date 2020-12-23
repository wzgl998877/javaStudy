package com.zt.javastudy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zt.javastudy.work.TestFastJson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class JavaStudyApplicationTests {

    @Test
    void testFastJson() {
        String string = "{\n" +
                "        \"buyer_login_id\":\"66***001\",\n" +
                "        \"buyer_pay_amount\":\"11.11\",\n" +
                "        \"buyer_user_id\":\"00000100600101\",\n" +
                "        \"coupon_amount\":\"0\",\n" +
                "        \"currency\":\"MOP\",\n" +
                "        \"discount_amount\":\"0.00\",\n" +
                "        \"merchant_discount_amt\":\"0.00\",\n" +
                "        \"out_trans_id\":\"1001153720201218153744684781\",\n" +
                "        \"pay_channel_type\":\"mpay\",\n" +
                "        \"pay_time\":\"2020-12-18 15:37:57\",\n" +
                "        \"platform_discount_amt\":\"0.00\",\n" +
                "        \"receipt_amount\":\"11.11\",\n" +
                "        \"result_code\":\"0000\",\n" +
                "        \"result_msg\":\"成功\",\n" +
                "        \"trade_no\":\"2020121815375600000082\",\n" +
                "        \"trans_amount\":\"11.11\",\n" +
                "        \"trans_id\":\"2020121815375610000001\",\n" +
                "        \"trans_status\":\"SUCCESS\",\n" +
                "        \"user_pay_amount\":\"11.11\",\n" +
                "        \"user_pay_ccy\":\"MOP\",\n" +
                "        \"user_site\":\"MO\",\n" +
                "\"coupon_detail\":[{\"coupon_id\":\"00001\",\"amount\":\"10.00\" ,\"name\":\"抵扣券\",\"type\":\"DISCOUNT_VOUCHER\",\"memo\":\" XX 優惠券\"}]\n" +
                "}";

        string ="{\"coupon_detail\":[\n" +
                "        {\n" +
                "            \"coupon_id\":\"00001\",\n" +
                "            \"amount\":\"10.00\",\n" +
                "            \"name\":\"抵扣券\",\n" +
                "            \"type\":\"DISCOUNT_VOUCHER\",\n" +
                "            \"memo\":\" XX 優惠券\"\n" +
                "        }]}";
        TestFastJson testFastJson = JSONObject.parseObject(string, TestFastJson.class);

        System.out.println(testFastJson);
        System.out.println(JSON.toJSONString(testFastJson));
    }

}
