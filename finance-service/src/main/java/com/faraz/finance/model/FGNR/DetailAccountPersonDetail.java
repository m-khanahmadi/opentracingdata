package com.faraz.finance.model.FGNR;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "DETAILACCOUNTDETAIL", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DetailAccountPersonDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DETAILACCOUNTID")
    private DetailAccount detailAccount;

    @ManyToOne
    @JoinColumn(name = "CLASSIFICATIONVALUEID")
    private ClassificationValue classificationValue;

    @ManyToOne
    @JoinColumn(name = "GROUPINGID")
    private Grouping grouping;

    @Column(name = "Code")
    @NonNull
    private Integer code;

    @Column(name = "CREDIT")
    private Long credit;

    @Column(name = "DESCRIPTION")
    private String description;

}
