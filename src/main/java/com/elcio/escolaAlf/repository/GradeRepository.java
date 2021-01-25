package com.elcio.escolaAlf.repository;

import com.elcio.escolaAlf.dto.GradeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeDTO, Long> {


}
