package com.faraz.finance.controller.FGNR.dto;

import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.Ledger;
import com.faraz.finance.model.FGNR.SubsidiaryLedger;
import com.faraz.finance.model.FSYS.AccountNature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class SubsidiaryLedgerDTO {

    private Integer id;

    @Length(min = 2, max = 100)
//    @Pattern(regexp = "[آ-ی ]+")
    @NotNull
    @NotBlank
    private String faTitle;

    @Length(min = 2, max = 50)
//    @Pattern(regexp = "^[a-zA-Z]{3,}$")
    @NotNull
    @NotBlank
    private String enTitle;

    @NotNull
    private Integer code;

    @Length(min = 2, max = 250)
    @Pattern(regexp = "[آ-ی ]+")
    @NotNull
    @NotBlank
    private String description;

    private Company companyId;
    private Ledger ledgerId;

    @NotNull
    private AccountNature accountNatureId;


    private SubsidiaryLedger contraryNatureSubsidiaryId;

    public SubsidiaryLedger toModel(Integer companyId) {
        SubsidiaryLedger subsidiaryLedger = SubsidiaryLedger.builder().build();
        BeanUtils.copyProperties(this,subsidiaryLedger);
        subsidiaryLedger.setCompanyId(Company.builder().id(companyId).build());
        return subsidiaryLedger;
    }

    public static SubsidiaryLedgerDTO fromModel(SubsidiaryLedger subsidiaryLedger) {
        SubsidiaryLedgerDTO dto = SubsidiaryLedgerDTO.builder().build();
        BeanUtils.copyProperties(subsidiaryLedger, dto);
        return dto;
    }

}