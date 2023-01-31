package com.faraz.finance.model.FGNR;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String family;

    private String nationalCode;
    private Boolean isDeleted = false;
    private Date birthDate;
    private Integer personnelCode;
    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company companyId;
}
