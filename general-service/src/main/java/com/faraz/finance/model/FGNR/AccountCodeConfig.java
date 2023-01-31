package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.DetailAccountGroupLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTCODECONFIG", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class AccountCodeConfig {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LENGROUP")
    private Short lenGroup;

    @Column(name = "LENLEDGER")
    private Short lenLedger;

    @Column(name = "LENSUBSIDIARY")
    private Short lenSubsidiary;

    @Column(name = "LENDETAIL")
    private Short lenDetail;

    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company companyId;

    @ManyToOne
    @JoinColumn(name = "DETAILACCOUNTGROUPLEVELID")
    private DetailAccountGroupLevel detailAccountGroupLevelId;

    @Column(name = "HASRELATEDDETAILACCOUNTCODEWITHTYPECODE")
    @Builder.Default
    private Boolean hasRelatedDetailAccountCodeWithTypeCode = true;

}
