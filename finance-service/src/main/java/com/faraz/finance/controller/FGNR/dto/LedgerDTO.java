package com.faraz.finance.controller.FGNR.dto;


import com.faraz.finance.model.FGNR.AccountGroup;
import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.Ledger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class LedgerDTO {

    private Integer id;

    @Length(min = 2, max = 100)
    @NotBlank
    private String faTitle;

    @Length(min = 2, max = 50)
    @NotBlank
    private String enTitle;

    @NotNull
    private Integer code;

    @Length(min = 2, max = 250)
    @NotBlank
    private String description;

    @NotNull
    private AccountGroup accountGroupId;

    public static LedgerDTO fromModel(Ledger ledger) {
        LedgerDTO ledgerDTO = LedgerDTO.builder().build();
        BeanUtils.copyProperties(ledger, ledgerDTO);
        return ledgerDTO;
    }

    public Ledger toModel(Integer companyId) {
        Ledger ledger = Ledger.builder().build();
        BeanUtils.copyProperties(this, ledger);
        ledger.setCompanyId(Company.builder().id(companyId).build());
        return ledger;
    }
}
