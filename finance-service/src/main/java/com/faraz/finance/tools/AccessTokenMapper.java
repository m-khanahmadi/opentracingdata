package com.faraz.finance.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenMapper {

    private String access_token;
    private Integer id;
    private String userName;
    private String name;
    private Integer companyId;
    private Integer personId;


}
