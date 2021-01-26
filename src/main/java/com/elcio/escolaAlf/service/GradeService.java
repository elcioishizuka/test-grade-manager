package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.*;
import com.elcio.escolaAlf.entity.Grade;
import com.elcio.escolaAlf.enums.Subject;
import com.elcio.escolaAlf.mapper.GradeMapper;
import com.elcio.escolaAlf.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private GradeRepository gradeRepository;

    private TestAnswersService testAnswersService;

    private AnswerkeyService answerkeyService;

    private StudentService studentService;

    private final GradeMapper gradeMapper = GradeMapper.INSTANCE;

    @Autowired
    public GradeService(GradeRepository gradeRepository, TestAnswersService testAnswersService,
                        AnswerkeyService answerkeyService, StudentService studentService){
        this.gradeRepository = gradeRepository;
        this.testAnswersService = testAnswersService;
        this.answerkeyService = answerkeyService;
        this.studentService = studentService;
    }

    public List<GradeDTO> listStudentGrades(){
        // Get information from each class
        Set<StudentDTO> allStudents = studentService.listAllStudents();
        List<TestAnswersDTO> allTestAnswers = testAnswersService.listAllTestAnswers();
        List<AnswerkeyDTO> allAnswerkeys = answerkeyService.listAllAnswerkeys();

        List<String> names = allStudents.stream().map(StudentDTO::getName).collect(Collectors.toList());
        List<String> lastNames = allStudents.stream().map(StudentDTO::getLastName).collect(Collectors.toList());
        List<String> studentIds = allStudents.stream().map(StudentDTO::getStudentId).collect(Collectors.toList());

        List<String> testStudentIds = allTestAnswers.stream().map(TestAnswersDTO::getStudent).map(StudentDTO::getStudentId).collect(Collectors.toList());
        List<Subject> testSubjects = allTestAnswers.stream().map(TestAnswersDTO::getTestInfo).map(TestInfoDTO::getSubject).collect(Collectors.toList());
        List<String> testTestNumbers = allTestAnswers.stream().map(TestAnswersDTO::getTestInfo).map(TestInfoDTO::getTestNumber).collect(Collectors.toList());
        List<List<AnswerDTO>> testAnswers = allTestAnswers.stream().map(TestAnswersDTO::getAnswers).collect(Collectors.toList());

        List<Subject> answerkeySubjects = allAnswerkeys.stream().map(AnswerkeyDTO::getTestInfo).map(TestInfoDTO::getSubject).collect(Collectors.toList());
        List<String> answerkeyTestNumbers = allAnswerkeys.stream().map(AnswerkeyDTO::getTestInfo).map(TestInfoDTO::getTestNumber).collect(Collectors.toList());
        List<List<AnswerDTO>> answerkeyAnswers = allAnswerkeys.stream().map(AnswerkeyDTO::getAnswers).collect(Collectors.toList());

        // Instance of new list of GradeDTO
        List<GradeDTO> listOfGrades = new ArrayList<>();

        // Deleting content of table "grade" before calculating and saving grades
        deleteGradeTable();

        for (int i = 0; i < studentIds.size(); i++) {
            for (int j = 0; j < testAnswers.size(); j++) {
                if(studentIds.get(i).equals(testStudentIds.get(j))){
                    for (int k = 0; k < answerkeyAnswers.size(); k++) {
                        if(testSubjects.get(j).equals(answerkeySubjects.get(k))
                                && testTestNumbers.get(j).equals(answerkeyTestNumbers.get(k))){

                            List<String> singleTestQuestions = testAnswers.get(j).stream().map(AnswerDTO::getQuestion).collect(Collectors.toList());
                            List<String> singleTestAnswers = testAnswers.get(j).stream().map(AnswerDTO::getAnswer).collect(Collectors.toList());

                            List<String> singleAnswerkeyQuestions = answerkeyAnswers.get(k).stream().map(AnswerDTO::getQuestion).collect(Collectors.toList());
                            List<String> singleAnswerkeyAnswers = answerkeyAnswers.get(k).stream().map(AnswerDTO::getAnswer).collect(Collectors.toList());
                            List<Integer> singleAnswerkeyWeight = answerkeyAnswers.get(k).stream().map(AnswerDTO::getWeight).collect(Collectors.toList());

                            Double partialGrade = 0.;
                            Double grade = 0.;
                            Double totalWeight = 0.;
                            for (int m = 0; m < singleAnswerkeyQuestions.size(); m++) {
                                if(singleAnswerkeyAnswers.get(m) == singleTestAnswers.get(m)){
                                    partialGrade += singleAnswerkeyWeight.get(m);
                                }
                                totalWeight += singleAnswerkeyWeight.get(m);
                            }

                            grade = partialGrade / totalWeight * 10 ;
                            GradeDTO gradeDTO = GradeDTO.builder()
                                    .testInfo(TestInfoDTO.builder()
                                            .testNumber(testTestNumbers.get(k))
                                            .subject(testSubjects.get(k))
                                            .build())
                                    .student(StudentDTO.builder()
                                            .studentId(studentIds.get(i))
                                            .name(names.get(i))
                                            .lastName(lastNames.get(i))
                                            .build())
                                    .grade(grade)
                                    .build();

                            GradeDTO savedGradeDTO = saveGrade(gradeDTO);
                            listOfGrades.add(savedGradeDTO);
                        }
                    }
                }
            }
        }
        return listOfGrades;
    }

    public GradeDTO saveGrade(GradeDTO gradeDTO) {
        Grade gradeToSave = gradeMapper.toModel(gradeDTO);
        Grade savedGrade = gradeRepository.save(gradeToSave);
        GradeDTO savedGradeDTO = gradeMapper.toDTO(savedGrade);
        return savedGradeDTO;
    }

    public void deleteGradeTable(){
        gradeRepository.deleteAll();
    }


}
