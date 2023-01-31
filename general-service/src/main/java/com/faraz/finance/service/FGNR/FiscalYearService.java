package com.faraz.finance.service.FGNR;


import com.faraz.finance.model.FGNR.FiscalYear;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface FiscalYearService {

    FiscalYear create(FiscalYear fiscalYear);
    FiscalYear update(FiscalYear fiscalYear);
    Boolean delete(Long id);
    Optional<List<FiscalYear>> getAll(Integer companyID);

}
