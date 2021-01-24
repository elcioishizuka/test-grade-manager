package com.elcio.escolaAlf.exception;

public class AnswerkeyAlreadyRegistered extends Exception{

    public AnswerkeyAlreadyRegistered(String subject, String testNumber){
        super(String.format("The answer key for subject %s and test number %s was already registered.", subject, testNumber));
    }

}
