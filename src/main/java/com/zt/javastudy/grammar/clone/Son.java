package com.zt.javastudy.grammar.clone;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengtao on 2022/3/26
 */
@Data
public class Son implements Cloneable{
    private String age;
    private Father father;

    @Override
    protected Son clone() throws CloneNotSupportedException {
        Son son =  (Son) super.clone();
        son.setFather(father.clone());
        return son;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Son son1 = new Son();
        son1.setAge("1");
        List<Son> list = new ArrayList<>();
        list.add(son1);
        String s  = JSONObject.toJSONString(list);
        List<Son> list1 = JSONArray.parseArray(s, Son.class);
        list1.get(0).setAge("1000");
        list1.forEach(son -> System.out.println(son.getAge()));
        list.forEach(son -> System.out.println(son.getAge()));
    }
}
