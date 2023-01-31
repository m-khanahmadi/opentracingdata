package com.faraz.finance.model.FGNR;

import com.faraz.finance.model.FSYS.CategoryType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "DETAILACCOUNTBANK", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Grouping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANYID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "PARENTID")
    private Grouping parent;

    @Column(name = "Code")
    @NonNull
    private Integer code;

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

    @Column(name = "ISDEFAULT")
    @Builder.Default
    private Boolean isDefafult = false;

    @Column(name = "ISDELETED")
    @Builder.Default
    private Boolean isDeleted = false;

    @Length(max = 1000)
    @Column(name = "PATH")
    private String path;

    @ManyToOne
    @JoinColumn(name = "CATEGORYTYPEID")
    private CategoryType categoryType;
}
