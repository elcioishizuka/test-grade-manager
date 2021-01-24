package com.elcio.escolaAlf.controller;


import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.repository.AnswerkeyRepository;
import com.elcio.escolaAlf.service.AnswerkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/answerkeys")
public class AnswerkeyController {

    private AnswerkeyService answerkeyService;

    @Autowired
    public AnswerkeyController(AnswerkeyService answerkeyService){
        this.answerkeyService = answerkeyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerkeyDTO createAnswerkey(@RequestBody @Valid AnswerkeyDTO answerkeyDTO){
        AnswerkeyDTO savedAnswerkeyDTO = answerkeyService.createAnswerkey(answerkeyDTO);
        return savedAnswerkeyDTO;
    }

//    @GetMapping
//    public List<Answerkey> listAll() {
//        List<Answerkey> allAnswerkey = answerkeyRepository.findAll();
//        return allAnswerkey;
//    }

}
