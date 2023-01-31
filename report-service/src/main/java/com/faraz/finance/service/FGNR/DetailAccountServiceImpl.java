package com.faraz.finance.service.FGNR;

import com.faraz.finance.controller.FGNR.dto.DetailAccountCrateDTO;
import com.faraz.finance.exception.ClientException;
import com.faraz.finance.model.FGNR.*;
import com.faraz.finance.model.FSYS.DetailAccountGroupType;
import com.faraz.finance.repository.FGNR.AccountCodeConfigRepository;
import com.faraz.finance.repository.FGNR.Treasurer.TreasurerRepository;
import com.faraz.finance.repository.FGNR.detailAccount.DetailAccountRepository;
import com.faraz.finance.repository.FGNR.detailAccountAddress.DetailAccountAddressRepositoryRepositoryCustom;
import com.faraz.finance.repository.FGNR.detailAccountBank.DetailAccountBankRepository;
import com.faraz.finance.repository.FGNR.detailAccountBankAccount.DetailAccountBankAccountRepository;
import com.faraz.finance.repository.FGNR.detailAccountGroup.DetailAccountGroupRepository;
import com.faraz.finance.repository.FSYS.detailAccountGroupType.DetailAccountGroupTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailAccountServiceImpl implements DetailAccountService {

    private final DetailAccountRepository detailAccountRepository;
    private final AccountCodeConfigRepository accountCodeConfigRepository;
    private final DetailAccountGroupRepository detailAccountGroupRepository;
    private final DetailAccountGroupTypeRepository detailAccountGroupTypeRepository;
    private final DetailAccountBankAccountRepository detailAccountBankAccountRepository;
    private final TreasurerRepository treasurerRepository;
    private final DetailAccountAddressRepositoryRepositoryCustom detailAccountAddressRepository;
    private final DetailAccountBankRepository detailAccountBankRepository;

    @Override
    public DetailAccount create(DetailAccountCrateDTO detailAccountCrateDTO) {

        Optional<AccountCodeConfig> optionalAccountCodeConfig = accountCodeConfigRepository
                .findByCompanyId_Id(detailAccountCrateDTO.getCompanyId().getId());

        if (optionalAccountCodeConfig.isEmpty()) {
            throw new ClientException("error.account.code.config.not.found");
        }

        if (detailAccountCrateDTO.getCode().toString().length() != optionalAccountCodeConfig.get().getLenDetail()) {
            throw new ClientException("error.code.length");
        }

        Optional<DetailAccountGroup> optionalDetailAccountGroup = detailAccountGroupRepository
                .findByIdAndIsDeletedIsFalse(detailAccountCrateDTO.getDetailAccountGroup().getId());

        if (optionalDetailAccountGroup.isEmpty()) {
            throw new ClientException("error.detail.account.group.not.found", String.valueOf(detailAccountCrateDTO.getDetailAccountGroup().getId()));
        }

        int lenDetail = optionalAccountCodeConfig.get().getLenDetail();
        String parentCode = detailAccountCrateDTO.getCode().toString().substring(0, lenDetail);
        String detailAccountGroupCode = optionalDetailAccountGroup.get().getCode().toString();
        if (!parentCode.equals(detailAccountGroupCode)) {
            throw new ClientException("error.parent.code.mismatch");
        }

        Optional<DetailAccountGroupType> optionalDetailAccountGroupType = detailAccountGroupTypeRepository
                .findById(optionalDetailAccountGroup.get().getDetailAccountGroupTypeId().getId());

        if (optionalDetailAccountGroupType.isEmpty()) {
            throw new ClientException("error.detail.account.group.type.not.found", String.valueOf(
                    detailAccountCrateDTO.getDetailAccountGroup().getDetailAccountGroupTypeId().getId())
            );
        }

        return checkRequirementsAndSaveDetailAccount(detailAccountCrateDTO, optionalDetailAccountGroupType.get());

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    protected DetailAccount checkRequirementsAndSaveDetailAccount(DetailAccountCrateDTO detailAccountCrateDTO, DetailAccountGroupType detailAccountGroupType) {
        DetailAccount detailAccount = detailAccountRepository.create(detailAccountCrateDTO.toModel());
        switch (detailAccountGroupType.getFaTitle()) {
            case "حساب بانکی":
                if (detailAccountCrateDTO.getDetailAccountBankAccount() == null)
                    throw new ClientException("error.detail.account.bank.account.empty");
                DetailAccountBankAccount detailAccountBankAccount = detailAccountCrateDTO.getDetailAccountBankAccount();
                detailAccountBankAccount.setDetailAccount(detailAccount);
                detailAccountBankAccountRepository.create(detailAccountCrateDTO.getDetailAccountBankAccount());
                break;
            case "صندوق":
            case "تنخواه":
                if (detailAccountCrateDTO.getTreasurer() == null)
                    throw new ClientException("error.treasurer.empty");
                Treasurer treasurer = detailAccountCrateDTO.getTreasurer();
                treasurer.setOwner(detailAccount);
                treasurerRepository.create(treasurer);
                break;
            case "شخص":
                if (detailAccountCrateDTO.getDetailAccountAddress() == null)
                    throw new ClientException("error.detail.account.address.empty");
                if (detailAccountCrateDTO.getDetailAccountBank() == null) {
                    throw new ClientException("error.detail.account.bank.empty");
                }
                DetailAccountAddress detailAccountAddress = detailAccountCrateDTO.getDetailAccountAddress();
                detailAccountAddress.setDetailAccount(detailAccount);
                DetailAccountBank detailAccountBank = detailAccountCrateDTO.getDetailAccountBank();
                detailAccountBank.setDetailAccount(detailAccount);
                detailAccountAddressRepository.customSave(detailAccountAddress);
                detailAccountBankRepository.create(detailAccountBank);
                break;
        }

        return detailAccount;
    }
}
