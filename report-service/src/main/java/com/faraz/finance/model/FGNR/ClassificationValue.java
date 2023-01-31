package com.faraz.finance.model.FGNR;

import com.faraz.finance.model.FSYS.CategoryType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CLASSIFICATIONVALUE", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ClassificationValue {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "CATEGORYTYPEID")
    private CategoryType categorytype;

    @NonNull
    @NotBlank
    @Length(max = 50)
    @Column(name = "FATITLE")
    private String faTitle;

    @Column(name = "ENTITLE")
    @NonNull
    @NotBlank
    @Length(max = 50)
    private String enTitle;

    @Column(name = "Code")
    @NonNull
    private Integer code;

    @Column(name = "ISDEFAULT")
    @Builder.Default
    private Boolean isDefafult = false;

    @Column(name = "ISDELETED")
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(name = "DESCRIPTION")
    private String description;


}
