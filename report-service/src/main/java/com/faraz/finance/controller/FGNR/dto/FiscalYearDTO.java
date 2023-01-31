package com.faraz.finance.controller.FGNR.dto;


import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.FiscalYear;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class FiscalYearDTO {

    @Id
    private Long id;

    @NonNull
    @NotEmpty
    @Length(max = 50)
    private String title;

    private Company company;

    private Date startDate;

    private Date endDate;

    public static FiscalYearDTO fromModel(FiscalYear fiscalYear) {
        FiscalYearDTO fiscalYearDTO = FiscalYearDTO.builder().build();
        BeanUtils.copyProperties(fiscalYear, fiscalYearDTO);
        return fiscalYearDTO;
    }

    public FiscalYear toModel(Integer companyId) {
        FiscalYear fiscalYear = FiscalYear.builder().build();
        BeanUtils.copyProperties(this, fiscalYear);
        fiscalYear.setCompany(Company.builder().id(companyId).build());
        return fiscalYear;
    }
    
}
