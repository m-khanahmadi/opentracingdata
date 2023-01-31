package com.faraz.finance.model.FGNR;

import com.faraz.finance.model.FSEC.CompanyKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMPANY", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Company implements Serializable {

    private static final long serialVersionUID = -343928414972524678L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "companyKeyId")
    private CompanyKey companyKeyId;

    private String faTitle;

    private String enTitle;

    private String code;

    private Boolean isDeleted;


}
