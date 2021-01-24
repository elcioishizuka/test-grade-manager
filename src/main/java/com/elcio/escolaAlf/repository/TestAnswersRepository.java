package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.TestAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestAnswersRepository extends JpaRepository<TestAnswers, Long> {

    List<TestAnswers> findByTestInfo_Subject(Enum subject);

    List<TestAnswers> findByTestInfo_SubjectAndTestInfo_TestNumber(Enum subject, String testNumber);
}
