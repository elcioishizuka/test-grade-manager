package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.entity.TestAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByStudentId(String studentID);

}
