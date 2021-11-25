package com.zt.javastudy;


import com.alibaba.fastjson.JSON;
import com.zt.javastudy.map.struct.Apple;
import com.zt.javastudy.map.struct.AppleMapper;
import com.zt.javastudy.map.struct.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class JavaStudyApplicationTests {
    @Autowired
    AppleMapper appleMapper;
    @Test
    void test() {
        Fruit fruit = new Fruit();
        fruit.setColor1("red");
        fruit.setSize("s");
        fruit.setPrice("10");
        Apple apple = appleMapper.fruitToApple(fruit);
        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(fruit);
        List<Apple> appleList = appleMapper.listToAList(fruitList);
        log.info("mapstrut:{}", JSON.toJSONString(apple));
        log.info("mapstrut:{}", JSON.toJSONString(appleList));
    }
}
