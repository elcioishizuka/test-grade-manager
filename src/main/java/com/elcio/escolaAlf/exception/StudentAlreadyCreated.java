package com.elcio.escolaAlf.exception;

import javax.validation.constraints.NotEmpty;

public class StudentAlreadyCreated extends Throwable {
    public StudentAlreadyCreated(@NotEmpty String studentId) {
    }
}
