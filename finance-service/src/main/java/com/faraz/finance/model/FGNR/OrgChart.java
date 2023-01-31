package com.faraz.finance.model.FGNR;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "ORGCHART", schema = "FGNR")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrgChart implements Serializable {

    private static final long serialVersionUID = -1996740946048632213L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Short id;


    @NotNull
    @NotBlank
    private String title;


    @NotNull
    @NotBlank
    private String code;


    private String path;

    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "parentId")
    @NotNull
    private OrgChart parentId;

    @ManyToOne
    @JoinColumn(name = "zoneId")
    @NotNull
    private Zone zoneId;

    @ManyToOne
    @JoinColumn(name = "companyId")
    @NotNull
    private Company companyId;

}
