package com.elcio.escolaAlf.controller;


import com.elcio.escolaAlf.entity.Answerkey;
import com.elcio.escolaAlf.repository.AnswerkeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answerkeys")
public class AnswerkeyController {

    private AnswerkeyRepository answerkeyRepository;

    @Autowired
    public AnswerkeyController(AnswerkeyRepository answerkeyRepository){
        this.answerkeyRepository = answerkeyRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Answerkey createAnswerkey(@RequestBody Answerkey answerkey){
        Answerkey savedAnswerkey = answerkeyRepository.save(answerkey);
        return savedAnswerkey;
    }

    @GetMapping
    public List<Answerkey> listAll() {
        List<Answerkey> allAnswerkey = answerkeyRepository.findAll();
        return allAnswerkey;
    }

}
