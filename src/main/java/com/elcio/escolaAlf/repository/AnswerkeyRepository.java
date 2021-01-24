package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.Answerkey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerkeyRepository extends JpaRepository<Answerkey, Long> {

    List<Answerkey> findByTestInfo_Subject(Enum subject);

    List<Answerkey> findByTestInfo_SubjectAndTestInfo_TestNumber(Enum subject, String testNumber);
}
