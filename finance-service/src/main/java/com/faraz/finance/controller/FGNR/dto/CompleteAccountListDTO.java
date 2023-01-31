package com.faraz.finance.controller.FGNR.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CompleteAccountListDTO {


    private Integer accountGroupId;

    private Integer ledgerId;

    private Integer subsidiaryLedgerId;

    private Integer accountGroupCode;

    private String accountGroupFaTitle;

    private String accountGroupEnTitle;

    private String accountGroupTypeFaTitle;

    private String accountGroupTypeEnTitle;

    private Integer ledgerCode;

    private String ledgerFaTitle;

    private String ledgerEnTitle;

    private Integer subsidiaryLedgerCode;

    private String subsidiaryLedgerFaTitle;

    private String subsidiaryLedgerEnTitle;

    private String subsidiaryLedgerNatureFaTitle;
    private String subsidiaryLedgerNatureEnTitle;
    private Boolean subsidiaryLedgerIsActive;
}
