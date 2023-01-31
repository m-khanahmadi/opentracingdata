package com.faraz.finance.service.FGNR;

import com.faraz.finance.model.FGNR.NumberingMethod;

import java.util.List;

public interface NumberingMethodService {
    List<NumberingMethod> getAll(Integer companyId);

    NumberingMethod create(NumberingMethod numberingMethod);

    NumberingMethod delete(Integer id);
}
