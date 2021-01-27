package com.elcio.escolaAlf.controller;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.dto.GradeDTO;
import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.exception.AnswerkeyAlreadyRegistered;
import com.elcio.escolaAlf.service.AnswerkeyService;
import com.elcio.escolaAlf.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


//    @GetMapping("/list")
//    public List<GradeDTO> list() {
//        List<GradeDTO> allGrades = gradeService.listStudentGrades();
//        return allGrades;
//    }



}