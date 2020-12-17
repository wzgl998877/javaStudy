package com.zt.javastudy;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zhengtao
 * @description 集合扩容
 * @date 2020/12/11
 */
public class StudyList {
    public static void main(String[] args) {
        // 不指定容量
        List<Integer> list = new ArrayList<>();
        // 构造一个初始容量为10的空列表
        for (int i=0;i<15;i++){
            list.add(i);
            // ArrayList中add方法是
//            public boolean add(E e) {
//                ensureCapacityInternal(size + 1);  // Increments modCount!!
//                1.得到最小容量，size从0开始加一，如果list是默认的空列表，那么就返回10与size + 1中的大值，如果不是默认的列表直接返回size+1，得到了最小容量minCapacity
//                2.modCount尺寸修改的次数加一，
//                if (minCapacity - elementData.length > 0) 检查此时列表需要的最小容量是否大于列表的长度，如果大于则去扩容
//                   grow(minCapacity);
//             private void grow(int minCapacity) {
//                // overflow-conscious code
//                int oldCapacity = elementData.length;
//                int newCapacity = oldCapacity + (oldCapacity >> 1);
//                相当于每次扩容为1.5倍
//                if (newCapacity - minCapacity < 0)
//                    newCapacity = minCapacity;
//                if (newCapacity - MAX_ARRAY_SIZE > 0)
//                    newCapacity = hugeCapacity(minCapacity);
//                // minCapacity is usually close to size, so this is a win:
//                elementData = Arrays.copyOf(elementData, newCapacity);
//                把数据copy到新扩容的列表上来
//            }
//                elementData[size++] = e;
//                return true;
//            }
        }
        list.add(15);
//        由于list创建时的容量为10，所以当list中的数据超过10时，会自动扩容，每次扩容为1.5倍，所以是，10 15 22...
        // 2 指定容量
        List<Integer> list1 = new ArrayList<>(5);
//        由于list创建时指定了容量，所以list中的数据超过给定的容量即5时，会自动扩容，5 7 10...
        // 构造具有指定初始容量的空列表
        for(int i=0;i<10;i++){
            list1.add(i);
        }

    }

    public String predictPartyVictory(String senate) {
//        Deque<Character> deque = new ArrayDeque<>();
        return "";
    }


}
