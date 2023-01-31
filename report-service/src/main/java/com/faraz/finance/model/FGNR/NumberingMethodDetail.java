package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.FormAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NUMBERINGMETHODDETAIL", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class NumberingMethodDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "NUMBERINGMETHODID")
    @NotNull
    private NumberingMethod numberingMethodId;


    @ManyToOne
    @JoinColumn(name = "FORMATTRIBUTEID")
    @NotNull
    private FormAttribute formAttributeId;
}
