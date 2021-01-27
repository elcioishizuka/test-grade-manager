package com.elcio.escolaAlf.controller;

import com.elcio.escolaAlf.dto.GradeDTO;
import com.elcio.escolaAlf.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/grades")
public class GradeController {

    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService){
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<GradeDTO> listAllGrades() {
        List<GradeDTO> allGrades = gradeService.listStudentGrades();
        return allGrades;
    }

    @GetMapping("/{subject}/{studentId}")
    public List<GradeDTO> listBySubjectAndStudentId (@PathVariable("subject") String subject, @PathVariable("studentId") String studentId){
        List<GradeDTO> gradesBySubjectAndStudentId = gradeService.listGradesBySubjectAndStudentId(subject, studentId);
        return gradesBySubjectAndStudentId;
    }

}