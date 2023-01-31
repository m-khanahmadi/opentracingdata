package com.faraz.finance.model.FSYS;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ACCOUNTTYPE", schema = "FSYS")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FATITLE")
    @Length(max = 50)
    @NotNull
    @NotBlank
    private String faTitle;

    @Column(name = "ENTITLE")
    @Length(max = 50)
    @NotNull
    @NotBlank
    private String enTitle;

}
