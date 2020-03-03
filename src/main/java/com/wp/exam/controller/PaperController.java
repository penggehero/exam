package com.wp.exam.controller;


import com.wp.exam.service.PaperService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/paper")
public class PaperController {

    private Map<String, Object> result = null;

    @Autowired
    private PaperService paperService;

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
            e.printStackTrace();
        } finally {
            if (newFile != null && newFile.exists())
                newFile.delete();
        }
        return result;
    }

    @PostMapping("/submit")
    public Map<String, Object> submit(@RequestBody Map<String, Object> param) {
        try{
            result = paperService.submit(param);
        }catch (Exception e){
            e.printStackTrace();
            result=null;
        }
        return result;
    }


//    @GetMapping("/search")
//    public Map<String, Object> search(@RequestParam Map<String, Object> param) {
//        try {
//            result = ServiceUtil.makeResult(teacherService.findbyConditions(param), null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
//        }
//        return result;
//    }


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
