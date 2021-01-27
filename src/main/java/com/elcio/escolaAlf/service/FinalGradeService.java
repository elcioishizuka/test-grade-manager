package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.*;
import com.elcio.escolaAlf.entity.Answerkey;
import com.elcio.escolaAlf.entity.FinalGrade;
import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.enums.Subject;
import com.elcio.escolaAlf.mapper.FinalGradeMapper;
import com.elcio.escolaAlf.repository.FinalGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FinalGradeService {

    private final FinalGradeMapper finalGradeMapper = FinalGradeMapper.INSTANCE;

    private FinalGradeRepository finalGradeRepository;

    private GradeService gradeService;

    private AnswerkeyService answerkeyService;

    private StudentService studentService;

    @Autowired
    public FinalGradeService (FinalGradeRepository finalGradeRepository, GradeService gradeService,
                              AnswerkeyService answerkeyService, StudentService studentService){

        this.finalGradeRepository = finalGradeRepository;
        this.gradeService = gradeService;
        this.answerkeyService = answerkeyService;
        this.studentService = studentService;

    }

//
//    public FinalGradeDTO listApprovedStudents() {
//        Set<StudentDTO> students = studentService.listAllStudents();
//        List<GradeDTO> allgrades = gradeService.listStudentGrades();
//        List<AnswerkeyDTO> allAnswerkeys = answerkeyService.listAllAnswerkeys();
//
//        List<String> studentIds = students.stream().map(StudentDTO::getStudentId).collect(Collectors.toList());
//        List<String> names = students.stream().map(StudentDTO::getName).collect(Collectors.toList());
//        List<String> lastNames = students.stream().map(StudentDTO::getLastName).collect(Collectors.toList());
//
//        List<Subject> subjects = allgrades.stream().map(GradeDTO::)
//
//
//
//
//
//
//
//
//
//    }

}
