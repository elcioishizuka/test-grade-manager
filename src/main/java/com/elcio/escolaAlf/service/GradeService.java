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
        // Get information from each DTO class
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


        for (int i = 0; i < allStudents.size(); i++) {
            for (int j = 0; j < allAnswerkeys.size(); j++) {
                Double grade = 0.;
                for (int k = 0; k < allTestAnswers.size(); k++) {
                    if (answerkeySubjects.get(j).equals(testSubjects.get(k))
                            && answerkeyTestNumbers.get(j).equals(testTestNumbers.get(k))
                            && studentIds.get(i).equals(testStudentIds.get(k))){

                        List<String> singleTestAnswers = testAnswers.get(k).stream().map(AnswerDTO::getAnswer).collect(Collectors.toList());

                        List<String> singleAnswerkeyAnswers = answerkeyAnswers.get(j).stream().map(AnswerDTO::getAnswer).collect(Collectors.toList());
                        List<Integer> singleAnswerkeyWeight = answerkeyAnswers.get(j).stream().map(AnswerDTO::getWeight).collect(Collectors.toList());

                        Double partialGrade = 0.;
                        Double totalWeight = 0.;
                        for (int m = 0; m < singleAnswerkeyAnswers.size(); m++) {
                            if(singleAnswerkeyAnswers.get(m) == singleTestAnswers.get(m)){
                                partialGrade += singleAnswerkeyWeight.get(m);
                            }
                            totalWeight += singleAnswerkeyWeight.get(m);
                        }
                        grade = partialGrade / totalWeight * 10;
                    }
                }
                GradeDTO gradeDTO = GradeDTO.builder()
                        .testInfo(TestInfoDTO.builder()
                                .testNumber(answerkeyTestNumbers.get(j))
                                .subject(answerkeySubjects.get(j))
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

    public List<GradeDTO> listGradesBySubjectAndStudentId(String subject, String studentId) {
        listStudentGrades();
        List<GradeDTO> foundGrades = gradeRepository.findByTestInfo_SubjectAndStudent_StudentId(Subject.valueOf(subject.toUpperCase()), studentId)
                .stream()
                .map(gradeMapper::toDTO)
                .collect(Collectors.toList());
        return foundGrades;

    }
}
