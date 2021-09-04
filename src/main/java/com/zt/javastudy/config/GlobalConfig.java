package com.zt.javastudy.config;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengtao
 * @description 全局配置类
 * @date 2021/4/23
 */
@Configuration
public class GlobalConfig {
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters(){
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        // 格式化输出，也就是换行等处理
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        SerializeConfig config = new SerializeConfig();
//        // 转为下划线
//        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
//        fastJsonConfig.setSerializeConfig(config);
//        converter.setFastJsonConfig(fastJsonConfig);
//        return new HttpMessageConverters(converter);
//    }
}
