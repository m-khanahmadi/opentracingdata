package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.PersonalityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DETAILACCOUNT", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DetailAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company companyId;

    @ManyToOne
    @JoinColumn(name = "BRANCHID")
    @NotNull
    private Branch branchId;

    @ManyToOne
    @JoinColumn(name = "DETAILACCOUNTGROUPID")
    @NotNull
    private DetailAccountGroup detailAccountGroup;

    @Column(name = "CODE")
    @NotNull
    private Long code;

    @Column(name = "ISACTIVE")
    @Builder.Default
    private Boolean isActive=true;

    @OneToOne
    @JoinColumn(name = "PERSONALITYTYPEID")
    @NotNull
    private PersonalityType personalityType;

    @Column(name = "ISSUPPLIER")
    private Boolean isSupplier;

    @Column(name = "ISMARKETER")
    private Boolean isMarketer;

    @Column(name = "ISCUSTOMER")
    private Boolean isCustomer;

    @Column(name = "ISOTHER")
    private Boolean isOther;

    @Column(name = "ISPERSONNEL")
    private Boolean isPersonnel;

    @Column(name = "TITLE")
    @Length(min = 3, max = 150)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "NAME")
    @Length(min = 3, max = 50)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "FAMILY")
    @Length(min = 3, max = 100)
    @NotNull
    @NotBlank
    private String family;

    @Column(name = "LEGALTITLE")
    @Length(min = 3, max = 100)
    private String legalTitle;

    @Column(name = "NATIONALCODE")
    @Length(min = 10, max = 10)
    private String nationalCode;

    @Column(name = "ECONOMICCODE")
    private Integer economicCode;

    @Column(name = "NATIONALID")
    private Integer nationalId;

    @Column(name = "REGISTERNO")
    private Integer registerNo;

    @Column(name = "WEBSITE")
    @Length(min = 10, max = 50)
    private String website;

    @Column(name = "EMAIL")
    @Length(max = 50)
    private String email;


    public void setCompanyId(Integer companyId) {
        this.companyId = Company.builder().id(companyId).build();
    }
}
