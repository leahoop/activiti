package com.example.activitidemo.test;

import java.lang.reflect.InvocationTargetException;

public class Test {


    @org.junit.Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        Record record = new Record();
//        record.setAchievement("1");
//        record.setAddr("aaa");
//        Map<String, String> map = initMap(record);
//        map.forEach((k, v) -> {
//            System.out.println("k is :"+ k +"----value is :"+ v);
//        });


    }

//    public Map<String, String> initMap(Object record) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Map<String, String> map = new HashMap<>();
//        Field[] field = record.getClass().getDeclaredFields();
//        for (int j = 0; j < field.length; j++) {
//            String name = field[j].getName();    //获取属性的名字
//            //将属性的首字符大写，方便构造get，set方法
//            Type type = field[j].getGenericType();//获取属性的类型
//            name = name.substring(0, 1).toUpperCase() + name.substring(1);
//            String value = null;
//            if ("java.lang.String".equals(type.getTypeName())) {
//                Method m = record.getClass().getMethod("get" + name);
//                value = (String) m.invoke(record);    //调用getter方法获取属性值
//            }
//
//            if ("java.lang.Integer".equals(type.getTypeName())) {
//                Method m = record.getClass().getMethod("get" + name);
//                value = (String) m.invoke(record);    //调用getter方法获取属性值
//
//            }
//
//            if (value != null) {
//                map.put(name, value);
//            } else {
//                map.put(name, "无");
//            }
//
//        }
//        return map;
//    }
}
