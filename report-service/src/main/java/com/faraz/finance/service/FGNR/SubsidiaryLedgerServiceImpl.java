package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.AccountCodeConfig;
import com.faraz.finance.model.FGNR.Ledger;
import com.faraz.finance.model.FGNR.SubsidiaryLedger;
import com.faraz.finance.repository.FGNR.*;
import com.faraz.finance.repository.FGNR.subLeder.CustomSubLedgerRepository;
import com.faraz.finance.repository.FGNR.subLeder.SubsidiaryLedgerRepository;
import com.faraz.finance.repository.FGNR.subLedgerDetail.CustomSubLedgerDetailRepository;
import com.faraz.finance.repository.FGNR.subLedgerDetail.SubsidiaryLedgerDetailRepository;
import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubsidiaryLedgerServiceImpl implements SubsidiaryLedgerService {

    private final SubsidiaryLedgerRepository subsidiaryLedgerRepository;
    private final AccountCodeConfigRepository accountCodeConfigRepository;
    private final LedgerRepository ledgerRepository;
    private final CustomSubLedgerRepository customSubLedgerRepository;
    private final CustomSubLedgerDetailRepository customSubLedgerDetailRepository;
    private final SubsidiaryLedgerDetailRepository subsidiaryLedgerDetailRepository;


    @Override
    public SubsidiaryLedger create(SubsidiaryLedger subsidiaryLedger) {
        System.out.println("companyId: "+subsidiaryLedger.getCompanyId().getId());
        Optional<AccountCodeConfig> optionalAccountCodeConfig = accountCodeConfigRepository.findByCompanyId_Id(subsidiaryLedger.getCompanyId().getId());

        if (optionalAccountCodeConfig.isEmpty()) {
            throw new ClientException("error.account.code.config.not.found");
        }

        Optional<Ledger> optionalLedger =
                ledgerRepository.findById(subsidiaryLedger.getLedgerId().getId());

        if (optionalLedger.isEmpty()) {
            throw new ClientException("error.ledger.not.found", String.valueOf(subsidiaryLedger.getLedgerId().getId()));
        }

        if (subsidiaryLedger.getCode().toString().length() != optionalAccountCodeConfig.get().getLenSubsidiary()) {
            throw new ClientException("error.code.length");
        }

        /*
         get lenLedger from accountCodeConfig and compare it vs
         first characters of subLedger's code
         */

        int lenLedger = optionalAccountCodeConfig.get().getLenLedger();
        String subLedgerParentCode = subsidiaryLedger.getCode().toString().substring(0, lenLedger);
        String ledgerCode = optionalLedger.get().getCode().toString();
        if (!subLedgerParentCode.equals(ledgerCode)) {
            throw new ClientException("error.parent.code.mismatch");
        }

        try {
            subsidiaryLedgerRepository.save(subsidiaryLedger);
        } catch (DataIntegrityViolationException e) {
            String property = StringUtil.getUniquePropertyFromException(e);
            throw new ClientException("error.unique.constrains.violation", property);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("error.internal.server");

        }
        return subsidiaryLedger;
    }

    @Override
    public SubsidiaryLedger update(SubsidiaryLedger subsidiaryLedger) {
        Optional<SubsidiaryLedger> optionalSubsidiaryLedger = subsidiaryLedgerRepository.findByIdAndCompanyId_Id(subsidiaryLedger.getId(), subsidiaryLedger.getCompanyId().getId());
        if (optionalSubsidiaryLedger.isEmpty()) {
            throw new ClientException("error.sub.ledger.not.found", String.valueOf(subsidiaryLedger.getId()));
        }
        return this.create(subsidiaryLedger);
    }

    @Override
    @Transactional
    public SubsidiaryLedger delete(Integer id) {
        Optional<SubsidiaryLedger> optionalSubsidiaryLedger = subsidiaryLedgerRepository.findById(id);
        if (optionalSubsidiaryLedger.isEmpty()) {
            throw new ClientException("error.sub.ledger.not.found", String.valueOf(id));
        }
        try {
            customSubLedgerDetailRepository.makeSubLedgerDetailDeletedBySubLedgerId(id);
            customSubLedgerRepository.makeSubLedgerDeletedById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("error.internal.server");
        }
        return optionalSubsidiaryLedger.get();
    }

    @Override
    public List<SubsidiaryLedger> getAll(Integer companyId) {
        Optional<List<SubsidiaryLedger>> optionalSubsidiaryLedgerList =
                subsidiaryLedgerRepository.findAllByCompanyId_Id(companyId);

        return optionalSubsidiaryLedgerList.orElseGet(ArrayList::new);
    }

    @Override
    public SubsidiaryLedger getById(Integer id) {
        Optional<SubsidiaryLedger> optionalSubsidiaryLedger = subsidiaryLedgerRepository.findById(id);
        if (optionalSubsidiaryLedger.isEmpty()) {
            throw new ClientException("error.sub.ledger.not.found", String.valueOf(id));
        }
        return optionalSubsidiaryLedger.get();
    }
}
