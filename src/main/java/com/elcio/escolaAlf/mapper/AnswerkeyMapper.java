package com.elcio.escolaAlf.mapper;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.entity.Answerkey;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerkeyMapper {

    AnswerkeyMapper INSTANCE = Mappers.getMapper(AnswerkeyMapper.class);

    Answerkey toModel(AnswerkeyDTO answerkeyDTO);

    AnswerkeyDTO toDTO(Answerkey answerkey);

}
