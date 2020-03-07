package com.wp.exam.controller;


import com.wp.exam.service.PaperService;
import com.wp.exam.util.ServiceUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        String path = TeacherController.class.getClassLoader().getResource("static").getPath() + File.separator + "file" + File.separator + "template.xls";
        File file = new File(path);
        /*String fileName=new String(file.getName().getBytes("utf-8"),"iso-8859-1"); //解决中文乱码问题*/
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=template.xls");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, statusCode);
    }


    @PostMapping("/import")
    public Map upload(@RequestParam MultipartFile file, @RequestParam Map<String, Object> param) {
        if (file == null) {
            result = ServiceUtil.makeResult(null, "上传试卷为空!");
            return result;
        }
        File newFile = null;
        try {
            String path = PaperController.class.getClassLoader().getResource("static").getPath() + UUID.randomUUID().toString() + file.getOriginalFilename();
            newFile = new File(path);
            //加将上传的文件保存到新文件
            file.transferTo(newFile);
            result = paperService.importPaper(newFile, param);
        } catch (Exception e) {
            result=ServiceUtil.makeResult(null,"文件类型不符合要求!");
            e.printStackTrace();
        } finally {
            if (newFile != null && newFile.exists())
                newFile.delete();
        }
        return result;
    }

    @PostMapping("/submit")
    public Map<String, Object> submit(@RequestBody Map<String, Object> param) {
        try {
            result = paperService.submit(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null,e.getMessage());
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
