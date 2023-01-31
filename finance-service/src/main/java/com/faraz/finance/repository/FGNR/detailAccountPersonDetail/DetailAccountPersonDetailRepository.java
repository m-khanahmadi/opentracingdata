package com.faraz.finance.repository.FGNR.detailAccountPersonDetail;


import com.faraz.finance.model.FGNR.DetailAccountPersonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailAccountPersonDetailRepository extends JpaRepository<DetailAccountPersonDetail, Long> {

    Optional<DetailAccountPersonDetail> findByDetailAccountAndClassificationValue(Long detailAccountId, Long classificationValueId);

}
