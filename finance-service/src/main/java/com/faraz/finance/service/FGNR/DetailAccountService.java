package com.faraz.finance.service.FGNR;

import com.faraz.finance.controller.FGNR.dto.DetailAccountCrateDTO;
import com.faraz.finance.model.FGNR.DetailAccount;

public interface DetailAccountService {
    DetailAccount create(DetailAccountCrateDTO detailAccountCrateDTO);
}
