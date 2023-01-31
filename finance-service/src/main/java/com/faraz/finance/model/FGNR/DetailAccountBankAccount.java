package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DETAILACCOUNTBANKACCOUNT", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DetailAccountBankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BANKBRANCHID")
    private BankBranch bankBranch;

    @ManyToOne
    @JoinColumn(name = "ACCOUNTTYPEID")
    private AccountType accountType;

    @Column(name = "ACCOUNTNUMBER")
    @Length(max = 20)
    private String accountNumber;

    @Column(name = "CARDNUMBER")
    @Length(max = 20)
    private String cardNumber;

    @Column(name = "SHABA")
    @Length(max = 26)
    private String shaba;

    @Column(name = "ACCOUNTCREATEDATE")
    private Date accountCreatedDate;

    @OneToOne
    @JoinColumn(name = "DETAILACCOUNTID")
    private DetailAccount detailAccount;


}
