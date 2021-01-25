package com.elcio.escolaAlf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public enum Subject {

    @Enumerated(EnumType.STRING)

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
