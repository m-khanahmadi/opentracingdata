package com.faraz.finance.controller.FGNR.dto;

import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.OrgChart;
import com.faraz.finance.model.FGNR.Person;
import com.faraz.finance.model.FGNR.PersonPost;
import com.faraz.finance.model.FSEC.User;
import com.faraz.finance.tools.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CreatePersonDTO {
    private Long id;
    //person fields
    @NotNull
    @Length(min = 3, max = 20)
    @Pattern(regexp = "[آ-ی ]+")
    private String firstName;

    @NotNull
    @Length(min = 3, max = 20)
    @Pattern(regexp = "[آ-ی ]+")
    private String family;

    @NotNull
    @Length(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]*$")
    private String nationalCode;

    @NotNull
    @Min(1)
    private Integer personnelCode;

    private Date birthDate;

    private Company companyId;

    //personPost fields

    @NotNull
    @Size(min = 1)
    private List<OrgChart> orgChartList = new ArrayList<>();

    //user fields
    //no _ or . at the beginning. no _ or . at the end. username is 8-20 characters long.
    // no __ or _. or ._ or .. inside
    // allowed characters [a-zA-Z0-9._]
    @Pattern(regexp = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    private String username;
    /**
     * At least one upper case English letter, (?=.*?[A-Z])
     * At least one lower case English letter, (?=.*?[a-z])
     * At least one digit, (?=.*?[0-9])
     * At least one special character, (?=.*?[#?!@$%^&*-])
     * Minimum eight in length .{8,} (with the anchors)
     */
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;

    public CreatePersonDTO fromModel(Person person) {
        CreatePersonDTO createPersonDTO = CreatePersonDTO.builder().build();
        BeanUtils.copyProperties(person, createPersonDTO);
        createPersonDTO.setFirstName(person.getName());
        createPersonDTO.setId(person.getId());
        return createPersonDTO;
    }

    public void setFirstName(String firstName) {

        this.firstName = StringUtil.replaceArabicChars(firstName);
    }

    public void setFamily(String family) {
        this.family = StringUtil.replaceArabicChars(family);
    }


    public Person toPerson() {
        return Person.builder()
                .family(this.family)
                .isDeleted(false)
                .name(this.firstName)
                .nationalCode(this.nationalCode)
                .personnelCode(this.personnelCode)
                .birthDate(this.birthDate)
                .companyId(this.companyId)
                .build();
    }

    public User toUser(Person person, PasswordEncoder passwordEncoder) {
        return User.builder()
                .companyId(this.companyId)
                .isActive(true)
                .name(this.username)
                .password(passwordEncoder.encode(this.password))
                .personId(person)

                .build();
    }

    public LinkedList<PersonPost> toPersonPostList(Person person) {
        LinkedList<PersonPost> personPostLinkedList = new LinkedList<>();
        for (OrgChart orgChart : this.orgChartList) {
            personPostLinkedList.add(PersonPost.builder()
                    .orgChartId(orgChart)
                    .personId(person)
                    .isDeleted(false)
                    .build()
            );
        }
        return personPostLinkedList;
    }
}
