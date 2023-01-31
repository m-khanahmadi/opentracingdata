package com.faraz.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class LoginDTO {
    private Long userId;
    private String username;
    private String password;
    private String roleTitle;
    private String firstname;
    private String family;
    private Long personId;
    private Integer personnelCode;
    private Integer companyId;
    private Integer farazKey;

}
