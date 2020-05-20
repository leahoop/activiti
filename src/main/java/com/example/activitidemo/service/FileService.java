package com.example.activitidemo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileService {

    public String getFile(HttpServletRequest request, HttpServletResponse response, int type) throws IOException {

        File f = ResourceUtils.getFile("C:\\Users\\Leahoop\\Desktop\\助学金申请表.doc");
        FileInputStream fis = null;
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
            response.setContentLength((int) f.length());
            response.setContentType("application/octet-stream;charset=UTF-8");
            byte[] b = new byte[1024];
            int i = 0;
             =new FileInputStream(f);
            ServletOutputStream out = response.getOutputStream();
            while ((i = fis.read(b)) > 0) {
                out.write(b, 0, i);
            }
            out.flush();

        } catch (final IOException e) {
            throw e;
        } finally {
            fis.close();
        }
        return null;

    }

}
