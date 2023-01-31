package com.faraz.finance.controller.FGNR.dto;

import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.Grouping;
import com.faraz.finance.model.FSYS.CategoryType;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class GroupingDTO {

    @Id
    private Long id;

    private Company company;

    private Grouping parent;

    @NonNull
    private Integer code;

    @NonNull
    @NotBlank
    @Length(max = 50)
    private String faTitle;

    @NonNull
    @NotBlank
    @Length(max = 50)
    private String enTitle;

    @Builder.Default
    private Boolean isDefafult = false;

    @Builder.Default
    private Boolean isDeleted = false;

    @Length(max = 1000)
    private String path;

    private CategoryType categoryType;

    public static GroupingDTO fromModel(Grouping grouping) {
        GroupingDTO groupingDTO = GroupingDTO.builder().build();
        BeanUtils.copyProperties(grouping, groupingDTO);
        return groupingDTO;
    }

    public Grouping toModel(Integer companyId) {
        Grouping grouping = Grouping.builder().build();
        BeanUtils.copyProperties(this, grouping);
        grouping.setCompany(Company.builder().id(companyId).build());
        return grouping;
    }
}
