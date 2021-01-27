package com.elcio.escolaAlf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public enum Status {

    @Enumerated(EnumType.STRING)

    APPROVED("Aprovado(a)"),
    REPROVED("Reprovado(a)");

    private final String description;

}