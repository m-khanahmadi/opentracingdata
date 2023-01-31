package com.faraz.finance.model.FGNR;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROLE", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String title;

    private String code;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company companyId;
}
