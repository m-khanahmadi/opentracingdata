package com.faraz.finance.service.FGNR;

import com.faraz.finance.controller.FGNR.dto.CompleteAccountListDTO;
import com.faraz.finance.model.FGNR.AccountGroup;

import java.util.List;

public interface AccountGroupService {
    AccountGroup create(AccountGroup accountGroup);

    AccountGroup update(AccountGroup accountGroup);

    AccountGroup deleteOne(Integer id, Integer companyId);

    List<CompleteAccountListDTO> getCompleteAccountList(Integer companyId);
}
