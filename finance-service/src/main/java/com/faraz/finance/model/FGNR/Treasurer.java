package com.faraz.finance.model.FGNR;

import com.faraz.finance.model.FSYS.CurrencyUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TREASURER", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Treasurer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "CURRENCYUNITID")
    private CurrencyUnit currencyUnit;

    @OneToOne
    @JoinColumn(name = "OWNERID")
    private DetailAccount owner;

    @Column(name = "CREATEDATE")
    private Date createDate;

}
