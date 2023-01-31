package com.faraz.finance.model.FGNR;


import com.faraz.finance.model.FSYS.AddressPositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DETAILACCOUNTADDRESS", schema = "FGNR")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DetailAccountAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ADDRESSPOSITIONTYPEID")
    @NotNull
    private AddressPositionType addressPositionType;

    @OneToOne
    @JoinColumn(name = "DETAILACCOUNTID")
    @NotNull
    private DetailAccount detailAccount;

    @Column(name = "ISDEFAULT")
    @Builder.Default
    private Boolean isDefault = true;

    @NotBlank
    @NotNull
    @Length(max = 250)
    @Column(name = "ADDRESS")
    private String address;

    @NotBlank
    @NotNull
    @Length(max = 20)
    @Column(name = "POSTALCODE")
    private String postalCode;

    @NotBlank
    @NotNull
    @Length(max = 20)
    @Column(name = "TEL")
    private String tel;

    @Length(max = 150)
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ISDELETED")
    private Boolean isDeleted;

}
