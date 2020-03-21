package com.wp.exam.controller;


import com.wp.exam.service.PaperService;
import com.wp.exam.util.ServiceUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/paper")
public class PaperController {

    private Map<String, Object> result = null;

    @Autowired
    private PaperService paperService;

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam Map<String, Object> param) {
        try {
            result = ServiceUtil.makeResult(paperService.search(param), null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    @GetMapping("/findByTeacher")
    public Map<String, Object> findByTeacher(@RequestParam Map<String, Object> param) {
        try {
            result = ServiceUtil.makeResult(paperService.findByTeacher(param), null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    /**
     * 考试模板下载
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("template.xls");
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] result = null;
        try {
            inputStream = classPathResource.getInputStream();
            outputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[2048];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
                outputStream.flush();
            }
            result = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
        }
        /*String fileName=new String(file.getName().getBytes("utf-8"),"iso-8859-1"); //解决中文乱码问题*/
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=template.xls");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<byte[]>(result, headers, statusCode);
    }


    @PostMapping("/import")
    public Map upload(@RequestParam MultipartFile file, @RequestParam Map<String, Object> param) {
        if (file == null) {
            result = ServiceUtil.makeResult(null, "上传试卷为空!");
            return result;
        }
        try {
            result = paperService.importPaper(file.getInputStream(), param);
        } catch (Exception e) {
            result = ServiceUtil.makeResult(null, "文件类型不符合要求!");
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/submit")
    public Map<String, Object> submit(@RequestBody Map<String, Object> param) {
        try {
            result = paperService.submit(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, e.getMessage());
        }
        return result;
    }


    @GetMapping("/getQuestion")
    public Map<String, Object> getQuestion(@RequestParam Map<String, Object> param) {
        try {
            result = paperService.getQuestion(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }


}
