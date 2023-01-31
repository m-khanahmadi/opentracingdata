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
@Table(name = "BANK", schema = "FSYS")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODE")
    private Integer code;

    @ManyToOne
    @JoinColumn(name = "BANKTYPEID")
    private BankType bankType;

    @Column(name = "TITLE")
    @Length(max = 20)
    @NotNull
    @NotBlank
    private String title;
}
