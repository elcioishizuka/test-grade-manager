package com.elcio.escolaAlf.exception;

public class QuestionWeightLessThanOrEqualToZero extends Exception {

    public QuestionWeightLessThanOrEqualToZero(){
        super("Weight of each question must be greater than 0");
    }
}
