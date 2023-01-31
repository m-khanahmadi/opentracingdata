package com.faraz.finance.controller.FGNR.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class GetPersonDTO {

    private Long id;

    private String name;

    private String family;

    private String nationalCode;

    private Integer personnelCode;

    private Date birthDate;

}
