package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.TestAnswersDTO;
import com.elcio.escolaAlf.entity.TestAnswers;
import com.elcio.escolaAlf.enums.Subject;
import com.elcio.escolaAlf.exception.AnswerAlreadyRegisteredToThisStudent;
import com.elcio.escolaAlf.exception.StudentIdDoesNotMatch;
import com.elcio.escolaAlf.exception.StudentQuantityExceeded;
import com.elcio.escolaAlf.mapper.TestAnswersMapper;
import com.elcio.escolaAlf.repository.TestAnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestAnswersService {

    private TestAnswersRepository testAnswersRepository;

    private StudentService studentService;

    private final TestAnswersMapper testAnswersMapper = TestAnswersMapper.INSTANCE;

    @Autowired
    public TestAnswersService(TestAnswersRepository testAnswersRepository, StudentService studentService){
        this.testAnswersRepository = testAnswersRepository;
        this.studentService = studentService;
    }

    public TestAnswersDTO createAnswers(TestAnswersDTO testAnswersDTO) throws StudentIdDoesNotMatch,
            AnswerAlreadyRegisteredToThisStudent, StudentQuantityExceeded {

        String studentId = testAnswersDTO.getStudent().getStudentId();
        verifyIfStudentIdIsCorrect(studentId, testAnswersDTO.getStudent().getStudentId());
        verifyIfAlreadyRegistered(studentId,
                testAnswersDTO.getTestInfo().getSubject().toString(),
                testAnswersDTO.getTestInfo().getTestNumber());
        studentService.verifyLimitOfStudentQuantity();

        TestAnswers testAnswersToSave = testAnswersMapper.toModel(testAnswersDTO);
        TestAnswers savedTestAnswers = testAnswersRepository.save(testAnswersToSave);
        TestAnswersDTO savedTestAnswersDTO = testAnswersMapper.toDTO(savedTestAnswers);
        return savedTestAnswersDTO;
    }

    public List<TestAnswersDTO> listAllTestAnswers() {
        List<TestAnswersDTO> allTestAnswers = testAnswersRepository.findAll()
                .stream()
                .map(testAnswersMapper::toDTO)
                .collect(Collectors.toList());
        return allTestAnswers;

    }

    public List<TestAnswersDTO> listTestAnswersBySubject(String subject) {
        List<TestAnswersDTO> foundTestAnswers = testAnswersRepository.findByTestInfo_Subject(Subject.valueOf(subject.toUpperCase()))
                .stream()
                .map(testAnswersMapper::toDTO)
                .collect(Collectors.toList());
        return foundTestAnswers;

    }

    public List<TestAnswersDTO> listTestAnswersBySubjectAndTestNumber(String subject, String testNumber) {
        List<TestAnswersDTO> foundTestAnswers = testAnswersRepository
                .findByTestInfo_SubjectAndTestInfo_TestNumber(Subject.valueOf(subject.toUpperCase()), testNumber)
                .stream()
                .map(testAnswersMapper::toDTO)
                .collect(Collectors.toList());
        return foundTestAnswers;

    }

    public List<TestAnswersDTO> listTestAnswersByStudentIdAndSubject(String studentId, String subject) {
        List<TestAnswersDTO> foundTestAnswers = testAnswersRepository
                .findByStudent_StudentIdAndTestInfo_Subject(studentId, Subject.valueOf(subject.toUpperCase()))
                .stream()
                .map(testAnswersMapper::toDTO)
                .collect(Collectors.toList());
        return foundTestAnswers;
    }

    public List<TestAnswersDTO> listTestAnswersByStudentIdAndSubjectAndTestNumber(String studentId, String subject, String testNumber) {
        List<TestAnswersDTO> foundTestAnswers = testAnswersRepository
                .findByStudent_StudentIdAndTestInfo_SubjectAndTestInfo_TestNumber(studentId, Subject.valueOf(subject.toUpperCase()), testNumber)
                .stream()
                .map(testAnswersMapper::toDTO)
                .collect(Collectors.toList());
        return foundTestAnswers;
    }

    public void deleteTestAnswersById(String id) {
        testAnswersRepository.deleteByStudent_StudentId(id);
    }

    private void verifyIfAlreadyRegistered(String studentId, String subject, String testNumber) throws AnswerAlreadyRegisteredToThisStudent {

        List<TestAnswers> SavedTestAnswers = testAnswersRepository
                .findByStudent_StudentIdAndTestInfo_SubjectAndTestInfo_TestNumber(studentId, Subject.valueOf(subject), testNumber);
        if(SavedTestAnswers.size() >= 1){
            throw new AnswerAlreadyRegisteredToThisStudent(studentId, subject, testNumber);
        }

    }

    private void verifyIfStudentIdIsCorrect(String studentIdLink, String studentIdRequest) throws StudentIdDoesNotMatch {

        if(!studentIdLink.equals(studentIdRequest)){
            throw new StudentIdDoesNotMatch(studentIdLink, studentIdRequest);
        }

    }


}
