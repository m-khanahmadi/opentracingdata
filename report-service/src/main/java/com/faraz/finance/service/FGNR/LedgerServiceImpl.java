package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.AccountCodeConfig;
import com.faraz.finance.model.FGNR.AccountGroup;
import com.faraz.finance.model.FGNR.Ledger;
import com.faraz.finance.model.FGNR.SubsidiaryLedger;
import com.faraz.finance.repository.FGNR.AccountCodeConfigRepository;
import com.faraz.finance.repository.FGNR.accountGroup.AccountGroupRepository;
import com.faraz.finance.repository.FGNR.LedgerRepository;
import com.faraz.finance.repository.FGNR.subLeder.SubsidiaryLedgerRepository;
import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LedgerServiceImpl implements LedgerService {

    private final LedgerRepository ledgerRepository;
    private final AccountGroupRepository accountGroupRepository;
    private final AccountCodeConfigRepository accountCodeConfigRepository;
    private final SubsidiaryLedgerRepository subsidiaryLedgerRepository;

    @Override
    public Ledger create(Ledger ledger) {
        Optional<AccountGroup> optionalAccountGroup =
                accountGroupRepository.findById(ledger.getAccountGroupId().getId());

        if (optionalAccountGroup.isEmpty()) {
            throw new ClientException("error.account.group.not.found", String.valueOf(ledger.getAccountGroupId().getId()));
        }

        Optional<AccountCodeConfig> optionalAccountCodeConfig = accountCodeConfigRepository.findByCompanyId_Id(ledger.getCompanyId().getId());

        if (optionalAccountCodeConfig.isEmpty()) {
            throw new ClientException("error.account.code.config.not.found");
        }

        if (ledger.getCode().toString().length() != optionalAccountCodeConfig.get().getLenLedger()) {
            throw new ClientException("error.code.length");
        }

        /*
         get lenGroup from accountCodeConfig and compare it vs
         first characters of ledger's code
         */
        int lenGroup = optionalAccountCodeConfig.get().getLenGroup();
        String ledgerParentCode = ledger.getCode().toString().substring(0, lenGroup);
        String accountGroupCOde = optionalAccountGroup.get().getCode().toString();
        if (!ledgerParentCode.equals(accountGroupCOde)) {
            throw new ClientException("error.parent.code.mismatch");
        }

        try {
            ledgerRepository.save(ledger);
        } catch (DataIntegrityViolationException e) {
            String property = StringUtil.getUniquePropertyFromException(e);
            throw new ClientException("error.unique.constrains.violation", property);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("error.internal.server");

        }
        return ledger;
    }

    @Override
    public Ledger update(Ledger ledger) {
        Optional<Ledger> optionalLedger = ledgerRepository.findById(ledger.getId());

        if (optionalLedger.isEmpty()) {
            throw new ClientException("error.ledger.not.found", String.valueOf(ledger.getId()));
        }

        Optional<SubsidiaryLedger> optionalSubsidiaryLedger = subsidiaryLedgerRepository
                .findByCompanyIdAndLedgerId(ledger.getCompanyId().getId(), ledger.getId());

        if (optionalSubsidiaryLedger.isPresent()) {
            ledger.setAccountGroupId(optionalLedger.get().getAccountGroupId());
            ledger.setCode(optionalLedger.get().getCode());
        }
        return this.create(ledger);
    }

    @Override
    public List<Ledger> getAll(Integer companyId) {
        Optional<List<Ledger>> optionalLedgerList =
                ledgerRepository.findAllByCompanyId(companyId);
        if (optionalLedgerList.isEmpty()) {
            throw new ClientException("error.no.ledger.found");
        }
        return optionalLedgerList.get();
    }

    @Override
    public Ledger delete(Integer companyId, Integer id) {
        Optional<Ledger> optionalLedger = ledgerRepository.findById(id);
        if (optionalLedger.isEmpty()) {
            throw new ClientException("error.ledger.not.found", String.valueOf(id));
        }

        Optional<SubsidiaryLedger> optionalSubsidiaryLedger = subsidiaryLedgerRepository
                .findByCompanyIdAndLedgerId(companyId, id);
        if (optionalSubsidiaryLedger.isPresent()) {
            throw new ClientException("error.unable.delete.ledger");
        }
        ledgerRepository.makeLedgerDeleted(id);
        return optionalLedger.get();
    }

    @Override
    public Ledger getOne(Integer id, Integer companyId) {
        Optional<Ledger> optionalLedger = ledgerRepository.findByIdAndCompanyId_IdAndIsDeletedIsFalse(id, companyId);
        if (optionalLedger.isEmpty()) {
            throw new ClientException("error.ledger.not.found", String.valueOf(id));
        }
        return optionalLedger.get();
    }


}
