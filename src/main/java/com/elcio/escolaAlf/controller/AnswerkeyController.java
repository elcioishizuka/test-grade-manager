package com.elcio.escolaAlf.controller;


import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.exception.AnswerkeyAlreadyRegistered;
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
    public AnswerkeyDTO createAnswerkey(@RequestBody @Valid AnswerkeyDTO answerkeyDTO) throws AnswerkeyAlreadyRegistered {
        AnswerkeyDTO savedAnswerkeyDTO = answerkeyService.createAnswerkey(answerkeyDTO);
        return savedAnswerkeyDTO;
    }

    @GetMapping
    public List<AnswerkeyDTO> listAllAnswerkeys() {
        List<AnswerkeyDTO> allAnswerkeys = answerkeyService.listAllAnswerkeys();
        return allAnswerkeys;
    }

    @GetMapping("/{subject}")
    public List<AnswerkeyDTO> listAnswerkeysBySubject(@PathVariable String subject) {
        List<AnswerkeyDTO> foundAnswerkeys = answerkeyService.listAnswerkeysBySubject(subject);
        return foundAnswerkeys;
    }

    @GetMapping("/{subject}/{testNumber}")
    public List<AnswerkeyDTO> listAnswerkeysBySubjectAndTestNumber(@PathVariable("subject") String subject, @PathVariable("testNumber") String testNumber) {
        List<AnswerkeyDTO> foundAnswerkeys = answerkeyService.listAnswerkeysBySubjectAndTestNumber(subject, testNumber);
        return foundAnswerkeys;
    }

    @DeleteMapping("/{id}")
    public void deleteAnswerkeyById(@PathVariable Long id){
        answerkeyService.deleteAnswerkeyById(id);
    }

}
