package com.faraz.finance.controller.FGNR.dto;

import com.faraz.finance.model.FGNR.ClassificationValue;
import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.DetailAccount;
import com.faraz.finance.model.FGNR.DetailAccountPersonDetail;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DetailAccountPersonDetailDTO {

    @Id
    private Long id;

    @NonNull
    private DetailAccount detailAccount;

    @NonNull
    private ClassificationValue classificationValue;

    private DetailAccountPersonDetail detailAccountPersonDetail;

    @NonNull
    private Integer code;

    private Long credit;

    private String description;

    public static DetailAccountPersonDetailDTO fromModel(DetailAccountPersonDetail detailAccountPersonDetail) {
        DetailAccountPersonDetailDTO detailAccountPersonDetailDTO = DetailAccountPersonDetailDTO.builder().build();
        BeanUtils.copyProperties(detailAccountPersonDetail, detailAccountPersonDetailDTO);
        return detailAccountPersonDetailDTO;
    }

    public DetailAccountPersonDetail toModel() {
        DetailAccountPersonDetail detailAccountPersonDetail = DetailAccountPersonDetail.builder().build();
        BeanUtils.copyProperties(this, detailAccountPersonDetail);
        return detailAccountPersonDetail;
    }
    

}
