package com.faraz.finance.service.FGNR;

import com.faraz.finance.model.FGNR.ClassificationValue;
import com.faraz.finance.model.FGNR.Grouping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GroupingService {

    Grouping create(Grouping grouping);
    Grouping update(Grouping grouping);
    Boolean delete(Long id);
    Boolean setDefault(Long id);
    Optional<List<Grouping>> getAll(Integer companyID, Long categoryTypeID);

}
