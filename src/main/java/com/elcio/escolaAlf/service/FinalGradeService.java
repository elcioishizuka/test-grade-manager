package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.FinalGradeDTO;
import com.elcio.escolaAlf.dto.GradeDTO;
import com.elcio.escolaAlf.dto.StudentDTO;
import com.elcio.escolaAlf.dto.TestInfoDTO;
import com.elcio.escolaAlf.entity.FinalGrade;
import com.elcio.escolaAlf.enums.Status;
import com.elcio.escolaAlf.enums.Subject;
import com.elcio.escolaAlf.mapper.FinalGradeMapper;
import com.elcio.escolaAlf.repository.FinalGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinalGradeService {

    private final FinalGradeMapper finalGradeMapper = FinalGradeMapper.INSTANCE;

    private FinalGradeRepository finalGradeRepository;

    private GradeService gradeService;

    private AnswerkeyService answerkeyService;

    private StudentService studentService;

    @Autowired
    public FinalGradeService (FinalGradeRepository finalGradeRepository, GradeService gradeService,
                              AnswerkeyService answerkeyService, StudentService studentService){

        this.finalGradeRepository = finalGradeRepository;
        this.gradeService = gradeService;
        this.answerkeyService = answerkeyService;
        this.studentService = studentService;

    }

    public List<FinalGradeDTO> listApprovedStudents(){
        List<StudentDTO> students = new ArrayList<>(new HashSet<>(studentService.listAllStudents()));
        List<GradeDTO> gradeDTOList = gradeService.listStudentGrades();

        List<String> studentIds = students.stream().map(StudentDTO::getStudentId).collect(Collectors.toList());
        List<Subject> subjects = new ArrayList<>(gradeDTOList.stream()
                .map(GradeDTO::getTestInfo)
                .map(TestInfoDTO::getSubject)
                .collect(Collectors.toSet()));

        // Instance of new list of GradeDTO
        List<FinalGradeDTO> listOfFinalGrades = new ArrayList<>();

        // Deleting content of table "grade" before calculating and saving grades
        deleteFinalGradeTable();


        for (int i = 0; i < studentIds.size(); i++) {

            for (int j = 0; j < subjects.size(); j++) {
                Double finalGrade = 0.;
                String status;

                List<GradeDTO> gradesPerSubjectPerPerson = gradeService.listGradesBySubjectAndStudentId(subjects.get(j).toString(), studentIds.get(i));

                List<Double> grades = gradesPerSubjectPerPerson.stream().map(GradeDTO::getGrade).collect(Collectors.toList());

                for (int k = 0; k < grades.size(); k++) {
                    finalGrade += grades.get(k);
                }

                finalGrade = finalGrade / grades.size();

                if (finalGrade >= 7){
                    status = "APPROVED";
                }else {
                    status = "REPROVED";
                }

                FinalGradeDTO finalGradeDTO = FinalGradeDTO.builder()
                        .subject(subjects.get(j))
                        .student(students.get(i))
                        .finalGrade(finalGrade)
                        .status(Status.valueOf(status))
                        .build();

                FinalGradeDTO savedFinalGradeDTO = saveFinalGrade(finalGradeDTO);
                listOfFinalGrades.add(savedFinalGradeDTO);

            }
        }
        return listOfFinalGrades;
    }

    public FinalGradeDTO saveFinalGrade(FinalGradeDTO finalGradeDTO) {
        FinalGrade finalGradeToSave = finalGradeMapper.toModel(finalGradeDTO);
        FinalGrade savedFinalGrade = finalGradeRepository.save(finalGradeToSave);
        FinalGradeDTO savedFinalGradeDTO = finalGradeMapper.toDTO(savedFinalGrade);
        return savedFinalGradeDTO;
    }

    public void deleteFinalGradeTable(){
        finalGradeRepository.deleteAll();
    }


//    public FinalGradeDTO listApprovedStudents() {
//        Set<StudentDTO> students = studentService.listAllStudents();
//        List<GradeDTO> gradeDTOList = gradeService.listStudentGrades();
//
//        List<String> studentIds = students.stream().map(StudentDTO::getStudentId).collect(Collectors.toList());
//        List<String> names = students.stream().map(StudentDTO::getName).collect(Collectors.toList());
//        List<String> lastNames = students.stream().map(StudentDTO::getLastName).collect(Collectors.toList());
//
//        List<Subject> gradeDTOListSubjects = gradeDTOList.stream().map(GradeDTO::getTestInfo).map(TestInfoDTO::getSubject).collect(Collectors.toList());
//        List<String> gradeDTOListStudentIds = gradeDTOList.stream().map(GradeDTO::getStudent).map(StudentDTO::getStudentId).collect(Collectors.toList());
//        List<String> testNumbers = gradeDTOList.stream().map(GradeDTO::getTestInfo).map(TestInfoDTO::getTestNumber).collect(Collectors.toList());
//        List<Double> grades = gradeDTOList.stream().map(GradeDTO::getGrade).collect(Collectors.toList());
//        List<String> allGradesStudentIds = gradeDTOList.stream().map(GradeDTO::getStudent).map(StudentDTO::getStudentId).collect(Collectors.toList());
//
//        for (int i = 0; i < studentIds.size(); i++) {
//            for (int j = 0; j < gradeDTOListSubjects.size(); j++) {
//
//                for
//
//                if (studentIds.get(i).equals(gradeDTOListStudentIds.get(j)))
//
//
//            }
//
//        }
//
//
//
//
//        private Long finalGradeId;
//
//        @Enumerated(EnumType.STRING)
//        private Subject subject;
//
//        @Valid
//        private Student student;
//
//        private Double finalGrade;
//
//        @Enumerated(EnumType.STRING)
//        private Status status;
//
//
//
//
//
//
//    }

}
