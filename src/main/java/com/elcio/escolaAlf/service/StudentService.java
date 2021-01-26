package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.dto.StudentDTO;
import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.exception.StudentAlreadyCreated;
import com.elcio.escolaAlf.exception.StudentQuantityExceeded;
import com.elcio.escolaAlf.mapper.StudentMapper;
import com.elcio.escolaAlf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) throws StudentAlreadyCreated, StudentQuantityExceeded {
        verifyIfAlreadyRegistered(studentDTO.getStudentId());
        verifyLimitOfStudentQuantity();
        Student studentToSave = studentMapper.toModel(studentDTO);
        Student savedStudent= studentRepository.save(studentToSave);
        StudentDTO savedStudentDTO = studentMapper.toDTO(savedStudent);
        return savedStudentDTO;
    }

    public void verifyLimitOfStudentQuantity() throws StudentQuantityExceeded {
        Set<StudentDTO> allStudentsDTO = listAllStudents();
        if (allStudentsDTO.size() >= 100){
            throw new StudentQuantityExceeded();
        }
    }

    private void verifyIfAlreadyRegistered(String studentId) throws StudentAlreadyCreated {
        List<Student> students = studentRepository.findByStudentId(studentId);
        if(students.size() >= 1){
            throw new StudentAlreadyCreated(studentId);
        }
    }

    public Set<StudentDTO> listAllStudents(){
        Set<StudentDTO> allStudentsDTO = studentRepository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .map(studentDTO -> new StudentDTO(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getLastName()))
                .collect(Collectors.toSet());
        return allStudentsDTO;
    }

}
