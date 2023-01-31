package com.faraz.finance.model.FSYS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MENU", schema = "FSYS")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String enTitle;
    private String faTitle;
    private String code;
    private Integer quickAccess;
    private String iconName;
    private String url;
    private Short treeLevel;
}
