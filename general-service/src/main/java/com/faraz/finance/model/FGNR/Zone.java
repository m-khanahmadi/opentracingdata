package com.faraz.finance.model.FGNR;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ZONE", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;


    private String title;

    private String code;

    private String path;

    private String taxCode;

    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Zone parentId;
}
