package com.h.mycourse.controller.aclass;

import com.h.mycourse.service.ClassService;
import com.h.mycourse.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class GradeController {
    @Autowired
    ClassService classService;

    //发布成绩页面
    @RequestMapping("/grade/release")
    public String releaseGrade(HttpServletRequest request, @RequestParam(name = "classID")long classID){
        request.setAttribute("aClass",classService.getByClassId(classID));
        return "gradeRelease";
    }

    //发布成绩操作
    @RequestMapping(value = "/grade/release/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadGrade(HttpServletRequest request, @RequestParam(name = "excel")MultipartFile excel, @RequestParam(name = "classID")long classID){
        Map<String, Double> info = ExcelUtil.getExcelInfo(excel);
        classService.releaseGrade(info, classID);
        request.setAttribute("msg","发布成功！<br><a href=\"/grade?classID="+classID+"\">查看成绩</a>");
        return "message";
    }

    //查看成绩情况
    @RequestMapping("/grade")
    public String downloadGrade(HttpServletRequest request, @RequestParam(name = "classID")long classID){
        request.setAttribute("aClass", classService.getByClassId(classID));
        return "grades";
    }
}
