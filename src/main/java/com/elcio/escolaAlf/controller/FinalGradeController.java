package com.elcio.escolaAlf.controller;

import com.elcio.escolaAlf.dto.FinalGradeDTO;
import com.elcio.escolaAlf.service.FinalGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<FinalGradeDTO> listStudentsStatus(){
        List<FinalGradeDTO> listStudentsStatus = finalGradeService.listAllFinalGrades();
        return listStudentsStatus;
    }

    @GetMapping("/{status}")
    public List<FinalGradeDTO> listApprovedStudents(@PathVariable String status){
        List<FinalGradeDTO> listOfApprovedStudents = finalGradeService.listStudentsByStatus(status);
        return  listOfApprovedStudents;
    }

    @GetMapping("/{subject}/{status}")
    public List<FinalGradeDTO> listApprovedStudentsBySubject(@PathVariable("subject") String subject, @PathVariable String status){
        List<FinalGradeDTO> listOfApprovedStudents = finalGradeService.listStudentsBySubjectAndStatus(subject, status);
        return  listOfApprovedStudents;
    }




}
