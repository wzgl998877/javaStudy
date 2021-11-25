package com.zt.javastudy.map.struct;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


/**
 * @author zhengtao on 2021/11/25
 */

@Mapper(componentModel = "spring")
public interface AppleMapper {

    @Mappings({
            @Mapping(source = "size", target = "magnitude"),
            @Mapping(source = "color1", target = "color"),
            @Mapping(source = "time", target = "money")
    })
    Apple fruitToApple(Fruit fruit);

    List<Apple> listToAList(List<Fruit> fruit);
}
