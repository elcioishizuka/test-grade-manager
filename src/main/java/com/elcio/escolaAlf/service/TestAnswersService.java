package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.TestAnswersDTO;
import com.elcio.escolaAlf.entity.TestAnswers;
import com.elcio.escolaAlf.enums.Subject;
import com.elcio.escolaAlf.mapper.TestAnswersMapper;
import com.elcio.escolaAlf.repository.TestAnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestAnswersService {

    private TestAnswersRepository testAnswersRepository;

    private final TestAnswersMapper testAnswersMapper = TestAnswersMapper.INSTANCE;

    @Autowired
    public TestAnswersService(TestAnswersRepository testAnswersRepository){
        this.testAnswersRepository = testAnswersRepository;
    }

    public TestAnswersDTO createAnswers(TestAnswersDTO testAnswersDTO) {
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
        List<TestAnswersDTO> foundTestAnswers = testAnswersRepository.findByTestInfo_SubjectAndTestInfo_TestNumber(Subject.valueOf(subject.toUpperCase()), testNumber)
                .stream()
                .map(testAnswersMapper::toDTO)
                .collect(Collectors.toList());
        return foundTestAnswers;

    }

    public void deleteTestAnswersById(Long id) {
        testAnswersRepository.deleteById(id);
    }
}
