package com.faraz.finance.model.FGNR;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORGCHARTROLE", schema = "FGNR")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class OrgChartRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgChartId")
    private OrgChart orgChartId;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role roleId;

    private Boolean isDeleted;
}
