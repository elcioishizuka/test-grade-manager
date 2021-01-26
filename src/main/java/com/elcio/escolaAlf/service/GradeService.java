package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.dto.GradeDTO;
import com.elcio.escolaAlf.dto.TestAnswersDTO;
import com.elcio.escolaAlf.mapper.GradeMapper;
import com.elcio.escolaAlf.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private GradeRepository gradeRepository;

    private TestAnswersService testAnswersService;

    private AnswerkeyService answerkeyService;

    private final GradeMapper gradeMapper = GradeMapper.INSTANCE;

    @Autowired
    public GradeService(GradeRepository gradeRepository, TestAnswersService testAnswersService,
                        AnswerkeyService answerkeyService){
        this.gradeRepository = gradeRepository;
        this.testAnswersService = testAnswersService;
        this.answerkeyService = answerkeyService;
    }

    public List<GradeDTO> listStudentGrades(){

        List<GradeDTO> list = getListOfGrades();

        return list;
    }


    public List<GradeDTO> listAllGrades() {
        //TO DO método para calcular as notas e retornar lista de notas
        List<AnswerkeyDTO> allAnswerkeys = answerkeyService.listAllAnswerkeys();
        List<TestAnswersDTO> allTestAnswers = testAnswersService.listAllTestAnswers();

        List<GradeDTO> list = getListOfGrades();

        return list;

    }

    private List<GradeDTO> getListOfGrades() {
        List<GradeDTO> list = getListOfGrades();

        return list;


    }


}
