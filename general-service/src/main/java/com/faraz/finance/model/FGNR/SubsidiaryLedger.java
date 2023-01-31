package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.AccountNature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SUBSIDIARYLEDGER", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SubsidiaryLedger implements Serializable {

    private static final long serialVersionUID = 3612886865630149639L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 2, max = 100)
    private String faTitle;

    @Length(min = 2, max = 50)
    private String enTitle;

    @Column(name = "CODE")
    private Integer code;

    @Builder.Default
    @Column(name = "ISDELETED")
    private boolean isDeleted=false;

    @Length(min = 2, max = 250)
    private String description;


    @JoinColumn(name = "COMPANYID")
    @ManyToOne
    private Company companyId;

    @ManyToOne
    @JoinColumn(name = "LEDGERID")
    private Ledger ledgerId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNTNATUREID")
    private AccountNature accountNatureId;

    @OneToOne
    @JoinColumn(name = "CONTRARYNATURESUBSIDIARYID")
    private SubsidiaryLedger contraryNatureSubsidiaryId;

    @Column(name = "ISACTIVE")
    @Builder.Default
    private boolean isActive =true;

}
