package com.faraz.finance.service.FGNR;


import com.faraz.finance.model.FGNR.DetailAccountPersonDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DetailAccountPersonDetailService {

    DetailAccountPersonDetail create(DetailAccountPersonDetail detailAccountPersonDetail);
    DetailAccountPersonDetail update(DetailAccountPersonDetail detailAccountPersonDetail);
    Boolean delete(Long id);
    Optional<DetailAccountPersonDetail> getOne(Long detailAccountID, Long classificationValueID);

}
