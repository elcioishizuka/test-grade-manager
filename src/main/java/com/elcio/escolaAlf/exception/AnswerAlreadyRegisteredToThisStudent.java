package com.elcio.escolaAlf.exception;

public class AnswerAlreadyRegisteredToThisStudent extends Exception{

    public AnswerAlreadyRegisteredToThisStudent (String studentId, String subject, String testNumber){
        super(String.format("The answers for subject %s - test number %s were already registered to student ID %s.", subject, testNumber, studentId));

    }

}
