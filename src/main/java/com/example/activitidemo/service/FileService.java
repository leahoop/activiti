package com.example.activitidemo.service;

import com.example.activitidemo.model.TypeEnum;
import lombok.Cleanup;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FileService {

    public String getFile(HttpServletRequest request, HttpServletResponse response, int type) throws IOException {
        String fileAddr;
        String fileName;

        String userAgent = request.getHeader("User-Agent");

        if(TypeEnum.SCHOLARSHIP.getCode() == type) {
            fileAddr = this.getClass().getClassLoader().getResource("scholarship.doc").getPath();
            fileName = TypeEnum.SCHOLARSHIP.getValue() + "申请表.doc";
        } else {
            fileAddr = this.getClass().getClassLoader().getResource("grant.doc").getPath();
            fileName = TypeEnum.GRANT.getValue() + "申请表.doc";
        }

        // 针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        File f = ResourceUtils.getFile(fileAddr);
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentLength((int) f.length());
            response.setContentType("application/octet-stream;charset=UTF-8");
            byte[] b = new byte[1024];
            int i = 0;
            @Cleanup
            FileInputStream fis =new FileInputStream(f);
            ServletOutputStream out = response.getOutputStream();
            while ((i = fis.read(b)) > 0) {
                out.write(b, 0, i);
            }
            out.flush();

        } catch (final IOException e) {
            throw e;
        }
        return null;

    }

}
