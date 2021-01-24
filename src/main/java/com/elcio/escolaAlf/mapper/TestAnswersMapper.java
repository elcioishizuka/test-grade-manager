package com.elcio.escolaAlf.mapper;

import com.elcio.escolaAlf.dto.TestAnswersDTO;
import com.elcio.escolaAlf.entity.TestAnswers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestAnswersMapper {

    TestAnswersMapper INSTANCE = Mappers.getMapper(TestAnswersMapper.class);

    TestAnswers toModel(TestAnswersDTO testAnswersDTO);

    TestAnswersDTO toDTO(TestAnswers testAnswers);

}
