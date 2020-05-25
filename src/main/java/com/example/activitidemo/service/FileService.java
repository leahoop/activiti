package com.example.activitidemo.service;

import com.example.activitidemo.model.AskLeave;
import com.example.activitidemo.model.Record;
import com.example.activitidemo.model.TypeEnum;
import com.example.activitidemo.utils.WordUtil;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileService {

    private final WordUtil wordUtil;

    private final AskLeaveService askLeaveService;

    private final RecordService recordService;

    public String getFile(HttpServletRequest request, HttpServletResponse response,
                          Integer type, Integer askId) throws IOException {
        String userAgent = request.getHeader("User-Agent");
        String fileAddr = null;

        AskLeave askLeave = askLeaveService.findById(askId);
        if (askLeave != null) {
            Optional<Record> byId = recordService.findById(askLeave.getRecordId());
            if (byId.isPresent()) {
                fileAddr = wordUtil.getTemplate(userAgent, type, byId.get());
            }
        }
        File f = ResourceUtils.getFile(fileAddr);
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
            response.setContentLength((int) f.length());
            response.setContentType("application/octet-stream;charset=UTF-8");
            byte[] b = new byte[1024];
            int i = 0;
            @Cleanup
            FileInputStream fis = new FileInputStream(f);
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
