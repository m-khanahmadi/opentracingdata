package com.faraz.finance.model.FGNR;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "LEDGER", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 2, max = 100)
    private String faTitle;

    @Length(min = 2, max = 50)
    private String enTitle;

    private Integer code;

    private boolean isDeleted;

    @Length(min = 2, max = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company companyId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNTGROUPID")
    private AccountGroup accountGroupId;
}
