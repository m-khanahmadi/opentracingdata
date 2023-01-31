package com.faraz.finance.model.FSYS;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "CURRENCYUNIT", schema = "FSYS")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class CurrencyUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FATITLE")
    @Length(max = 50)
    private String faTitle;

    @Column(name = "ENTITLE")
    @Length(max = 50)
    private String enTitle;

    @Column(name = "CODE")
    private Integer code;
}
