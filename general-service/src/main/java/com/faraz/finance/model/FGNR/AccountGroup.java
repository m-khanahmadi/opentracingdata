package com.faraz.finance.model.FGNR;

import com.faraz.finance.model.FSYS.AccountGroupType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTGROUP", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class AccountGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 2, max = 50)
    private String faTitle;

    @Length(min = 2, max = 50)
    private String enTitle;

    private Integer code;

    @ManyToOne
    @JoinColumn(name = "ACCOUNTGROUPTYPEID")
    private AccountGroupType accountGroupTypeId;

    @Length(min = 2, max = 250)
    private String description;


    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company companyId;

    @Builder.Default
    @Column(name = "ISDELETED")
    private boolean isDeleted = false;

}
