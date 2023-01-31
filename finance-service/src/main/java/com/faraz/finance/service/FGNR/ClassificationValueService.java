package com.faraz.finance.service.FGNR;

import com.faraz.finance.model.FGNR.ClassificationValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClassificationValueService {

    ClassificationValue create(ClassificationValue classificationValue);
    ClassificationValue update(ClassificationValue classificationValue);
    Boolean delete(Long id);
    Boolean setDefault(Long id);
    Optional<List<ClassificationValue>> getAll(Integer companyID, Long categoryTypeID);
}
