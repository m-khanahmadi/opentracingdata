package com.faraz.finance.service.FGNR;

import com.faraz.finance.model.FGNR.SubsidiaryLedger;

import java.util.List;

public interface SubsidiaryLedgerService {
    SubsidiaryLedger create(SubsidiaryLedger subsidiaryLedger);

    SubsidiaryLedger update(SubsidiaryLedger subsidiaryLedger);

    SubsidiaryLedger delete(Integer id);

    List<SubsidiaryLedger> getAll(Integer companyId);

    SubsidiaryLedger getById(Integer id);
}
