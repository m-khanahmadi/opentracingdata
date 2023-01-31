package com.faraz.finance.model.FSYS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "DETAILACCOUNTGROUPTYPE", schema = "FSYS")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DetailAccountGroupType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 2, max = 50)
    private String faTitle;

    @Length(min = 2, max = 50)
    private String enTitle;

    private Integer code;

}
