package com.example.activitidemo.utils;

import com.example.activitidemo.model.Record;
import com.example.activitidemo.model.TypeEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Cleanup;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class WordUtil {

    public String getTemplate(String userAgent, Integer type, Record record) {
        try {
            /** 初始化配置文件 **/
            Configuration configuration = new Configuration();
            /** 设置编码 **/
            configuration.setDefaultEncoding("utf-8");
            String fileDirectory = ClassLoader.getSystemClassLoader()
                    .getResource("ftlTemplate").getPath();

            /** 加载文件 **/
            configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
            /** 加载模板 **/
            String fileName;
            String outName;
            if (TypeEnum.SCHOLARSHIP.getCode().equals(type)) {
                fileName = "school.ftl";
                outName = record.getName() + TypeEnum.SCHOLARSHIP.getValue() + "申请表.doc";
            } else {
                fileName = "grant_use.ftl";
                outName = record.getName() + TypeEnum.GRANT.getValue() + "申请表.doc";
            }

            // 针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            Template template = configuration.getTemplate(fileName);
            /** 准备数据 **/
            Map<String, Object> dataMap = initMap(record);

            /** 指定输出word文件的路径 **/
            String outFilePath = ClassLoader.getSystemClassLoader()
                    .getResource("file").getPath() +
                    "/" + outName;
            // 针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                outFilePath = java.net.URLEncoder.encode(outFilePath, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                outFilePath = new String(outFilePath.getBytes("UTF-8"), "ISO-8859-1");
            }
            File docFile = new File(outFilePath);
            FileOutputStream fos = new FileOutputStream(docFile);
            @Cleanup
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
            template.process(dataMap, out);
            return outFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出错了");
        }
    }


    public Map<String, Object> initMap(Object record) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] field = record.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {
            String name = field[j].getName();    //获取属性的名字
            //将属性的首字符大写，方便构造get，set方法
            Type type = field[j].getGenericType();//获取属性的类型
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            Object value = null;
            if ("java.lang.String".equals(type.getTypeName())) {
                Method m = record.getClass().getMethod("get" + name);
                value = m.invoke(record);    //调用getter方法获取属性值
            }

            if ("java.lang.Integer".equals(type.getTypeName())) {
                Method m = record.getClass().getMethod("get" + name);
                value = m.invoke(record);    //调用getter方法获取属性值

            }

            if (value != null) {
                map.put(toLowerCaseFirstOne(name), value);
            } else {
                map.put(toLowerCaseFirstOne(name), "无");
            }

        }
        return map;
    }

    private static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        }
        return (new StringBuilder())
                .append(Character.toLowerCase(s.charAt(0)))
                .append(s.substring(1)).toString();
    }

}
