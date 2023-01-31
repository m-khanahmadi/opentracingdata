package com.faraz.finance.model.FGNR;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PERSONPOST", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PersonPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    private Person personId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgChartId")
    private OrgChart orgChartId;

    private Boolean isDeleted;

}
