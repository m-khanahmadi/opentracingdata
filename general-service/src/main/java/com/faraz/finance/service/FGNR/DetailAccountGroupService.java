package com.faraz.finance.service.FGNR;

import com.faraz.finance.model.FGNR.DetailAccountGroup;

import java.util.Set;

public interface DetailAccountGroupService {
    Set<DetailAccountGroup> getAll(Integer companyId);

    DetailAccountGroup delete(Integer id);

    DetailAccountGroup getOne(Integer id);

    DetailAccountGroup create(DetailAccountGroup detailAccountGroup);

    DetailAccountGroup update(DetailAccountGroup detailAccountGroup);
}
