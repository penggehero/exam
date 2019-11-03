package com.wp.exam.service;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.File;
import java.util.Map;

public interface PaperService {

    public String addPaper(File file,String name,Integer time,String teacherName) throws Exception;
}
