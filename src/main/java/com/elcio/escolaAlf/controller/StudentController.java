package com.elcio.escolaAlf.controller;

import com.elcio.escolaAlf.dto.StudentDTO;
import com.elcio.escolaAlf.exception.StudentAlreadyCreated;
import com.elcio.escolaAlf.exception.StudentQuantityExceeded;
import com.elcio.escolaAlf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createStudent(@RequestBody @Valid StudentDTO studentDTO) throws StudentAlreadyCreated,
            StudentQuantityExceeded {
        StudentDTO savedStudent = studentService.createStudent(studentDTO);
        return savedStudent;
    }

    @GetMapping
    public Set<StudentDTO> listAllStudents(){
        Set<StudentDTO> allStudents = studentService.listAllStudents();
        return allStudents;
    }



}
