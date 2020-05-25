package com.example.activitidemo.controller;

import com.example.activitidemo.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller

@RequiredArgsConstructor(onConstructor_=  @Autowired)
public class ExcelController {


    private final FileService fileService;

    // 处理下载word文档
    @RequestMapping("/download")
    public String  download(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam Integer fileType, Integer askId) throws Exception {
        return fileService.getFile(request, response, fileType, askId);
    }


}
