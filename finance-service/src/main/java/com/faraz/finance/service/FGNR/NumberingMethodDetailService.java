package com.faraz.finance.service.FGNR;

import com.faraz.finance.model.FGNR.NumberingMethodDetail;

import java.util.List;

public interface NumberingMethodDetailService {
    NumberingMethodDetail create(NumberingMethodDetail numberingMethodDetail);

    List<NumberingMethodDetail> getAll(Integer companyId);

    NumberingMethodDetail getOne(Integer id);

    NumberingMethodDetail delete(Integer id);
}
