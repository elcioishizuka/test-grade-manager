package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.builder.StudentDTOBuilder;
import com.elcio.escolaAlf.dto.StudentDTO;
import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.exception.StudentAlreadyCreated;
import com.elcio.escolaAlf.exception.StudentQuantityExceeded;
import com.elcio.escolaAlf.mapper.StudentMapper;
import com.elcio.escolaAlf.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentMapper studentMapper = StudentMapper.INSTANCE;

    @InjectMocks
    private StudentService studentService;

    @Test
    void whenStudentInformedThenItShouldBeCreated() throws StudentAlreadyCreated, StudentQuantityExceeded {
        // given
        StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        Student expectedSavedStudent = studentMapper.toModel(expectedStudentDTO);

        // when
        when(studentRepository.findByStudentId(expectedStudentDTO.getStudentId())).thenReturn(Collections.emptyList());
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());
        when(studentRepository.save(expectedSavedStudent)).thenReturn(expectedSavedStudent);

        // then
        StudentDTO savedStudentDTO = studentService.createStudent(expectedStudentDTO);

        // Hamcrest assertion
        assertThat(savedStudentDTO.getId(), is(equalTo(expectedStudentDTO.getId())));
        assertThat(savedStudentDTO.getStudentId(), is(equalTo(expectedStudentDTO.getStudentId())));
        assertThat(savedStudentDTO.getName(), is(equalTo(expectedStudentDTO.getName())));
        assertThat(savedStudentDTO.getLastName(), is(equalTo(expectedStudentDTO.getLastName())));

//        // junit assertion
//        assertEquals(expectedStudentDTO.getId(), savedStudentDTO.getId());
//        assertEquals(expectedStudentDTO.getStudentId(), savedStudentDTO.getStudentId());
//        assertEquals(expectedStudentDTO.getName(), savedStudentDTO.getName());
//        assertEquals(expectedStudentDTO.getLastName(), savedStudentDTO.getLastName());

    }

    @Test
    void whenStudentAlreadyRegisteredThenExceptionShouldBeThrown() throws StudentAlreadyCreated, StudentQuantityExceeded {
        // given
        StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        Student duplicatedStudent = studentMapper.toModel(expectedStudentDTO);

        // when
        when(studentRepository.findByStudentId(expectedStudentDTO.getStudentId())).thenReturn(List.of(duplicatedStudent));

        assertThrows(StudentAlreadyCreated.class, () -> studentService.createStudent(expectedStudentDTO));

    }

    @Test
    void whenStudentQuantityExceeds100ThenExceptionShouldBeThrown(){
        // given
        StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        List<Student> existingStudentList = new ArrayList<>();
        for (Long i = 0L; i < 100; i++) {
            StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
            Student student = studentMapper.toModel(studentDTO);
            student.setStudentId(i.toString());
            existingStudentList.add(student);
        }

        // when
        when(studentRepository.findByStudentId(expectedStudentDTO.getStudentId())).thenReturn(Collections.emptyList());
        doReturn(existingStudentList).when(studentRepository).findAll();

        assertThrows(StudentQuantityExceeded.class, () -> studentService.createStudent(expectedStudentDTO));


// *********************************************************************************************************************
// Alternative way to test using OngoingStubbing
// *********************************************************************************************************************
//        // given
//        StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
//        List<Student> existingStudentList = new ArrayList<>();
//
//        // when
//        when(studentRepository.findByStudentId(expectedStudentDTO.getStudentId())).thenReturn(Collections.emptyList());
//
//        OngoingStubbing stubbing = when(studentRepository.findAll());
//        for (Long i = 0L; i < 100; i++) {
//            StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
//            Student student = studentMapper.toModel(studentDTO);
//            student.setStudentId(i.toString());
//            existingStudentList.add(student);
//        }
//        stubbing.thenReturn(existingStudentList);
//
//        when(studentRepository.findAll()).thenReturn(existingStudentList);
//
//        assertThrows(StudentQuantityExceeded.class, () -> studentService.createStudent(expectedStudentDTO));

    }

}
