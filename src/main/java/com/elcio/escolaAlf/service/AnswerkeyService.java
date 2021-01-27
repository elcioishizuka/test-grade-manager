package com.elcio.escolaAlf.service;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.entity.Answerkey;
import com.elcio.escolaAlf.enums.Subject;
import com.elcio.escolaAlf.exception.AnswerkeyAlreadyRegistered;
import com.elcio.escolaAlf.mapper.AnswerkeyMapper;
import com.elcio.escolaAlf.repository.AnswerkeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerkeyService {

    private AnswerkeyRepository answerkeyRepository;

    private final AnswerkeyMapper answerkeyMapper = AnswerkeyMapper.INSTANCE;

    @Autowired
    public AnswerkeyService(AnswerkeyRepository answerkeyRepository){
        this.answerkeyRepository = answerkeyRepository;
    }

    public AnswerkeyDTO createAnswerkey(AnswerkeyDTO answerkeyDTO) throws AnswerkeyAlreadyRegistered {
        verifyIfAlreadyRegistered(answerkeyDTO.getTestInfo().getSubject().toString(), answerkeyDTO.getTestInfo().getTestNumber());
        Answerkey answerkeyToSave = answerkeyMapper.toModel(answerkeyDTO);
        Answerkey savedAnswerkey= answerkeyRepository.save(answerkeyToSave);
        AnswerkeyDTO savedAnswerkeyDTO = answerkeyMapper.toDTO(savedAnswerkey);
        return savedAnswerkeyDTO;
    }

    public List<AnswerkeyDTO> listAllAnswerkeys() {
        List<AnswerkeyDTO> allAnswerkeys = answerkeyRepository.findAll()
                .stream()
                .map(answerkeyMapper::toDTO)
                .collect(Collectors.toList());
        return allAnswerkeys;
    }

    public void deleteAnswerkeyById(Long id) {
        answerkeyRepository.deleteById(id);
    }

    public List<AnswerkeyDTO> listAnswerkeysBySubject(String subject){
        List<AnswerkeyDTO> foundAnswerkeys = answerkeyRepository.findByTestInfo_Subject(Subject.valueOf(subject.toUpperCase()))
                .stream()
                .map(answerkeyMapper::toDTO)
                .collect(Collectors.toList());
        return foundAnswerkeys;
    }

    public List<AnswerkeyDTO> listAnswerkeysBySubjectAndTestNumber(String subject, String testNumber) {
        List<AnswerkeyDTO> foundAnswerkeys = answerkeyRepository.findByTestInfo_SubjectAndTestInfo_TestNumber(Subject.valueOf(subject.toUpperCase()), testNumber)
                .stream()
                .map(answerkeyMapper::toDTO)
                .collect(Collectors.toList());
        return foundAnswerkeys;
    }

    private void verifyIfAlreadyRegistered(String subject, String testNumber) throws AnswerkeyAlreadyRegistered{
        List<Answerkey> SavedAnswerkey = answerkeyRepository.findByTestInfo_SubjectAndTestInfo_TestNumber(Subject.valueOf(subject), testNumber);
        if(SavedAnswerkey.size() >= 1){
            throw new AnswerkeyAlreadyRegistered(subject, testNumber);
        }

    }


}
