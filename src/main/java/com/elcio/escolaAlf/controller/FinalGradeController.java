package com.elcio.escolaAlf.controller;

import com.elcio.escolaAlf.dto.FinalGradeDTO;
import com.elcio.escolaAlf.service.FinalGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/finalgrades")
public class FinalGradeController {

    private FinalGradeService finalGradeService;

    @Autowired
    public FinalGradeController(FinalGradeService finalGradeService){
        this.finalGradeService = finalGradeService;
    }

    @GetMapping
    public List<FinalGradeDTO> listApprovedStudents(){
        List<FinalGradeDTO> listStudentsStatus = finalGradeService.listApprovedStudents();
        return listStudentsStatus;
    }

}
