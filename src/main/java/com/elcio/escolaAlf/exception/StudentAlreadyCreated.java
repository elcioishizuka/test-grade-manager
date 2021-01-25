package com.elcio.escolaAlf.exception;

public class StudentAlreadyCreated extends Exception {
    public StudentAlreadyCreated(String studentId) {
        super(String.format("Student ID %s was already registered.",studentId));
    }
}
