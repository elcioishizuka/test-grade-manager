package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.dto.StudentDTO;
import com.elcio.escolaAlf.entity.Answerkey;
import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.enums.Subject;
import com.elcio.escolaAlf.exception.AnswerkeyAlreadyRegistered;
import com.elcio.escolaAlf.exception.StudentAlreadyCreated;
import com.elcio.escolaAlf.mapper.AnswerkeyMapper;
import com.elcio.escolaAlf.mapper.StudentMapper;
import com.elcio.escolaAlf.repository.AnswerkeyRepository;
import com.elcio.escolaAlf.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) throws StudentAlreadyCreated {
        verifyIfAlreadyRegistered(studentDTO.getStudentId());
        Student studentToSave = studentMapper.toModel(studentDTO);
        Student savedStudent= studentRepository.save(studentToSave);
        StudentDTO savedStudentDTO = studentMapper.toDTO(savedStudent);
        return savedStudentDTO;
    }

    private void verifyIfAlreadyRegistered(String studentId) throws StudentAlreadyCreated {
        List<Student> students = studentRepository.findByStudentId(studentId);
        if(students.size() >= 1){
            throw new StudentAlreadyCreated(studentId);
        }

    }

}
