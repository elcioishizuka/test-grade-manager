package com.elcio.escolaAlf.exception;

public class StudentIdDoesNotMatch extends Exception {

    public StudentIdDoesNotMatch(String studentIdLink, String studentIdRequest){
        super(String.format("Student ID of the link %s does not match with student ID of the request %s", studentIdLink, studentIdRequest));
    }

}
