package com.faraz.finance.model.FSYS;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FORMATTRIBUTE", schema = "FSYS")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class FormAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Length(min = 3, max = 200)
    @Column(name = "FATITLE")
    private String faTitle;

    @NotNull
    @Length(min = 3, max = 200)
    @Column(name = "ENTITLE")
    private String enTitle;

    @ManyToOne
    @JoinColumn(name = "FORMID")
    private Form formId;
}
