package com.elcio.escolaAlf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Subject {

    PORTUGUES("Português"),
    MATEMATICA("Matemática"),
    HISTORIA("História"),
    GEOGRAFIA("Geografia"),
    FISICA("Física"),
    QUIMICA("Química"),
    EDUCACAOFISICA("Educação Física"),
    INGLES("Inglês");

    private final String description;

}
