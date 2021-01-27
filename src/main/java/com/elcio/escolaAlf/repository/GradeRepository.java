package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByTestInfo_SubjectAndStudent_StudentId(Enum subject, String studentId);


}
