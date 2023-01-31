package com.faraz.finance.controller.FGNR.dto;

import com.faraz.finance.model.FGNR.ClassificationValue;
import com.faraz.finance.model.FGNR.Company;
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
public class ClassificationValueDTO {

    @Id
    private Long id;

    private Company company;

    private CategoryType categorytype;

    @NonNull
    @NotBlank
    @Length(max = 50)
    private String faTitle;

    @NonNull
    @NotBlank
    @Length(max = 50)
    private String enTitle;

    @NonNull
    private  Integer code;

    private String description;

    public static ClassificationValueDTO fromModel(ClassificationValue classificationValue) {
        ClassificationValueDTO classificationValueDTO = ClassificationValueDTO.builder().build();
        BeanUtils.copyProperties(classificationValue, classificationValueDTO);
        return classificationValueDTO;
    }

    public ClassificationValue toModel(Integer companyId) {
        ClassificationValue classificationValue = ClassificationValue.builder().build();
        BeanUtils.copyProperties(this, classificationValue);
        classificationValue.setCompany(Company.builder().id(companyId).build());
        return classificationValue;
    }
}
