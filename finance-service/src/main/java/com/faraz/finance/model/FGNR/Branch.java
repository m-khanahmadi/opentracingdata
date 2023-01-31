package com.faraz.finance.model.FGNR;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "BRANCH", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Branch {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 2, max = 50)
    private String faTitle;

    @Length(min = 2, max = 50)
    private String enTitle;

    @Column(name = "CODE")
    private Integer code;

    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company companyId;

    @Column(name = "ISDELETED")
    private Boolean isDeleted;


}
