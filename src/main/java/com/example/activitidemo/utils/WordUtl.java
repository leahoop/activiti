package com.example.activitidemo.utils;

import com.example.activitidemo.model.Record;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Cleanup;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class WordUtl {


    public void ExportWord(Record record) throws Exception {
        /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
        /** 我的ftl文件是放在D盘的**/
        String fileDirectory = "D:\\";
        /** 加载文件 **/
        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        /** 加载模板 **/
        Template template = configuration.getTemplate("test2.ftl");
        /** 准备数据 **/
        Map<String, String> dataMap2 = new HashMap<>();

        /** 在ftl文件中有${textDeal}这个标签**/
        dataMap2.put("username", "aaa");
        dataMap2.put("addr", "bbb");

        /** 指定输出word文件的路径 **/
        String outFilePath = "D:\\myFreeMarker.doc";
        File docFile = new File(outFilePath);
        @Cleanup
        FileOutputStream fos = new FileOutputStream(docFile);
        @Cleanup
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap2, out);
    }


    public Map<String, String> objectToMap(Object obj) throws Exception {
        if (obj == null)
            return null;
        Map<String, String> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            String value = getter != null ? (String) getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

}
