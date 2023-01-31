package com.faraz.finance.model.FSEC;

import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "USERS", schema = "FSEC")
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private Boolean isActive = true;
    private Date expireDate;
    @OneToOne
    @JoinColumn(name = "personId")
    private Person personId;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company companyId;


}
