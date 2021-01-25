package com.elcio.escolaAlf.controller;

import com.elcio.escolaAlf.dto.TestAnswersDTO;
import com.elcio.escolaAlf.exception.AnswerAlreadyRegisteredToThisStudent;
import com.elcio.escolaAlf.exception.StudentIdDoesNotMatch;
import com.elcio.escolaAlf.service.TestAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/testanswers")
public class TestAnswersController {

    private TestAnswersService testAnswersService;

    @Autowired
    public TestAnswersController(TestAnswersService testAnswersService){
        this.testAnswersService = testAnswersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestAnswersDTO createTestAnswers(@RequestBody @Valid TestAnswersDTO testAnswersDTO) throws StudentIdDoesNotMatch, AnswerAlreadyRegisteredToThisStudent {
        TestAnswersDTO savedTestAnswersDTO = testAnswersService.createAnswers(testAnswersDTO);
        return savedTestAnswersDTO;
    }

//    @PostMapping("/{studentId}/registeranswers")
//    public TestAnswersDTO registerAnswers (@PathVariable String studentId,
//                                           @RequestBody @Valid TestAnswersDTO testAnswersDTO)
//            throws AnswerAlreadyRegisteredToThisStudent, StudentIdDoesNotMatch {
//        TestAnswersDTO savedTestAnswersDTO = testAnswersService.registerAnswers(studentId, testAnswersDTO);
//        return savedTestAnswersDTO;
//    }


    @GetMapping
    public List<TestAnswersDTO> listAllTestAnswers() {
        List<TestAnswersDTO> allTestAnswers = testAnswersService.listAllTestAnswers();
        return allTestAnswers;
    }

    @GetMapping("/{subject}")
    public List<TestAnswersDTO> listTestAnswersBySubject(@PathVariable String subject) {
        List<TestAnswersDTO> foundTestAnswers = testAnswersService.listTestAnswersBySubject(subject);
        return foundTestAnswers;
    }

//    @GetMapping("/{subject}/{testNumber}")
//    public List<TestAnswersDTO> listTestAnswersBySubjectAndTestNumber(@PathVariable("subject") String subject, @PathVariable("testNumber") String testNumber) {
//        List<TestAnswersDTO> foundTestAnswers = testAnswersService.listTestAnswersBySubjectAndTestNumber(subject, testNumber);
//        return foundTestAnswers;
//    }

    @GetMapping("/{studentId}/{subject}")
    public List<TestAnswersDTO> listTestAnswersByStudentIdAndSubject(@PathVariable("studentId") String studentId,
                                                                      @PathVariable("subject") String subject){
        List<TestAnswersDTO> foundTestAnswers = testAnswersService.listTestAnswersByStudentIdAndSubject(studentId, subject);
        return foundTestAnswers;
    }

    @GetMapping("/{studentId}/{subject}/{testNumber}")
    public List<TestAnswersDTO> listTestAnswersByStudentIdAndSubjectAndTestNumber
            (@PathVariable("studentId") String studentId,
             @PathVariable("subject") String subject,
             @PathVariable("testNumber") String testNumber) {

        List<TestAnswersDTO> foundTestAnswers = testAnswersService.listTestAnswersByStudentIdAndSubjectAndTestNumber(studentId, subject, testNumber);
        return foundTestAnswers;
    }


    @DeleteMapping("/{studentId}")
    public void deleteTestAnswersById(@PathVariable String studentId){
        testAnswersService.deleteTestAnswersById(studentId);
    }



}
