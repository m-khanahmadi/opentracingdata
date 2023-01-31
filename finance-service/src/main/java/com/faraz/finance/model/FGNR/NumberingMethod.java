package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.Form;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NUMBERINGMETHOD", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class NumberingMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "FORMID")
    @NotNull
    private Form formId;

    @Column(name = "STARTNUMBER")
    @NotNull
    private Integer startNumber;

    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company companyId;

    @Column(name = "ISACTIVE")
    @Builder.Default
    private Boolean isActive =true;

    public void setCompanyId(Integer companyId) {
        this.companyId = Company.builder().id(companyId).build();
    }

}
