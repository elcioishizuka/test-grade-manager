package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.FinalGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {

}
