package com.faraz.finance.model.FSYS;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CATEGORYTYPE", schema = "FSYS")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class CategoryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "DESCRIPTION")
    @Length(max = 200)
    private String description;

    @Column(name = "ISDEFAULT")
    @Builder.Default
    private Boolean isDefafult = false;

    @Column(name = "ISDELETED")
    @Builder.Default
    private Boolean isDeleted = false;


}
