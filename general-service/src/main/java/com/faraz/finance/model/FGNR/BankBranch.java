package com.faraz.finance.model.FGNR;

import com.faraz.finance.model.FSYS.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BANKBRANCH", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class BankBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "BANKID")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "BRANCHID")
    private Branch branch;

    @Column(name = "CODE")
    private Integer code;

    @Column(name = "TITLE")
    @Length(max = 50)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "TEL")
    @Length(max = 20)
    private String tel;

    @Column(name = "ADDRESS")
    @Length(max = 250)
    private String address;

    @Column(name = "FAX")
    @Length(max = 20)
    private String fax;

    @Column(name = "ISACTIVE")
    private Boolean isActive;


}
