package com.faraz.finance.model.FGNR;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SUBSIDIARYLEDGERDETAIL", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SubsidiaryLedgerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "SUBSIDIARYLEDGERID")
    private SubsidiaryLedger subsidiaryLedgerId;
    @OneToOne
    @JoinColumn(name = "DETAILACCOUNTGROUPID")
    private DetailAccountGroup detailAccountGroupId;


    private Boolean isDeleted;

}
