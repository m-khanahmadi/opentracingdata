package com.faraz.finance.controller.FGNR.dto;


import com.faraz.finance.model.FGNR.*;
import com.faraz.finance.model.FSYS.PersonalityType;
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
public class DetailAccountCrateDTO {

    private Long id;

    private Company companyId;

    @NotNull
    private Branch branchId;

    @NotNull
    private DetailAccountGroup detailAccountGroup;

    @NotNull
    private Long code;

    @Builder.Default
    private Boolean isActive = true;

    @NotNull
    private PersonalityType personalityType;

    private Boolean isSupplier;

    private Boolean isMarketer;

    private Boolean isCustomer;

    private Boolean isOther;

    private Boolean isPersonnel;

    @Length(min = 3, max = 150)
    @NotNull
    @NotBlank
    private String title;

    @Length(min = 3, max = 50)
    @NotNull
    @NotBlank
    private String name;

    @Length(min = 3, max = 100)
    @NotNull
    @NotBlank
    private String family;

    @Length(min = 3, max = 100)
    private String legalTitle;

    @Length(min = 10, max = 10)
    private String nationalCode;

    private Integer economicCode;

    private Integer nationalId;

    private Integer registerNo;

    @Length(min = 10, max = 50)
    private String website;

    @Length(max = 50)
    private String email;

    private DetailAccountAddress detailAccountAddress;

    private DetailAccountBank detailAccountBank;

    private DetailAccountBankAccount detailAccountBankAccount;

    private Treasurer treasurer;

    public void setCompanyId(Integer companyId) {
        this.companyId = Company.builder().id(companyId).build();
    }

    public DetailAccount toModel() {
        DetailAccount detailAccount = DetailAccount.builder().build();
        BeanUtils.copyProperties(this,detailAccount);
        return detailAccount;
    }


}
