package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.entity.Answer;
import com.elcio.escolaAlf.entity.Answerkey;
import com.elcio.escolaAlf.entity.TestInfo;
import com.elcio.escolaAlf.mapper.AnswerkeyMapper;
import com.elcio.escolaAlf.repository.AnswerkeyRepository;
import com.sun.xml.bind.v2.util.CollisionCheckStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerkeyService {

    private AnswerkeyRepository answerkeyRepository;

    private final AnswerkeyMapper answerkeyMapper = AnswerkeyMapper.INSTANCE;


    @Autowired
    public AnswerkeyService(AnswerkeyRepository answerkeyRepository){
        this.answerkeyRepository = answerkeyRepository;
    }

    public AnswerkeyDTO createAnswerkey(AnswerkeyDTO answerkeyDTO){
        Answerkey answerkeyToSave = answerkeyMapper.toModel(answerkeyDTO);
        Answerkey savedAnswerkey= answerkeyRepository.save(answerkeyToSave);
        AnswerkeyDTO savedAnswerkeyDTO = answerkeyMapper.toDTO(savedAnswerkey);
        return savedAnswerkeyDTO;
    }


}
