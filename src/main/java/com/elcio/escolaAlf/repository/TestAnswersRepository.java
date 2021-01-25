package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.entity.TestAnswers;
import com.elcio.escolaAlf.enums.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface TestAnswersRepository extends JpaRepository<TestAnswers, String> {

    List<TestAnswers> findByTestInfo_Subject(Enum subject);

    List<TestAnswers> findByTestInfo_SubjectAndTestInfo_TestNumber(Enum subject, String testNumber);

    List<TestAnswers> findByStudent_StudentIdAndTestInfo_Subject(String studentId, Subject valueOf);

    List<TestAnswers> findByStudent_StudentIdAndTestInfo_SubjectAndTestInfo_TestNumber(String studentId, Enum subject, String testNumber);

    TestAnswers findByStudent_StudentId(String studentID);

    void deleteByStudent_StudentId(String studentId);


}
