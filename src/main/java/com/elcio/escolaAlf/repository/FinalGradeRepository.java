package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.FinalGrade;
import com.elcio.escolaAlf.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {

    List<FinalGrade> findByStatus(Enum status);

    List<FinalGrade> findBySubjectAndStatus(Enum subject, Enum status);

    List<FinalGrade> findByStudent_StudentId(String studentId);
}
