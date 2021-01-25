package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.GradeDTO;
import com.elcio.escolaAlf.mapper.GradeMapper;
import com.elcio.escolaAlf.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private GradeRepository gradeRepository;

    private final GradeMapper gradeMapper = GradeMapper.INSTANCE;

    @Autowired
    public GradeService(GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }


    public List<GradeDTO> listAllGrades() {
        //TO DO método para calcular as notas e retornar lista de notas


    }
}
