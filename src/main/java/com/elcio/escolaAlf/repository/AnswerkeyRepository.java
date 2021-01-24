package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.Answerkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerkeyRepository extends JpaRepository<Answerkey, Long> {

    List<Answerkey> findByTestInfo_Subject(Enum subject);

    List<Answerkey> findByTestInfo_SubjectAndTestInfo_TestNumber(Enum subject, String testNumber);
}
