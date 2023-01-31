package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.AccountType;
import com.faraz.finance.model.FSYS.CurrencyUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "DETAILACCOUNTBANK", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DetailAccountBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "DETAILACCOUNTID")
    @NotNull
    private DetailAccount detailAccount;

    @OneToOne
    @JoinColumn(name = "BANKBRANCHID")
    @NotNull
    private BankBranch bankBranch;

    @OneToOne
    @JoinColumn(name = "ACCOUNTTYPEID")
    @NotNull
    private AccountType accountType;

    @Column(name = "CARDNUMBER")
    @Length(min = 16,max = 16)
    private String cardNumber;

    @Column(name = "ACCOUNTNUMBER")
    @Length(max = 20)
    private String accountNumber;

    @Column(name = "SHABA")
    @Length(min = 20,max = 20)
    private String shaba;

    @ManyToOne
    @JoinColumn(name = "CURRENCYUNITID")
    private CurrencyUnit currencyUnit;

    @Column(name = "ACCOUNTCREATEDATE")
    private Date accountCreateDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ISDELETED")
    @Builder.Default
    private Boolean isDeleted = false;
}
