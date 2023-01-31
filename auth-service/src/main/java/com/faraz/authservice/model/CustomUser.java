package com.faraz.authservice.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CustomUser extends User {


    private Long personId;
    private Long userId;
    private String name;
    private Integer farazKey;
    private Integer companyId;


    public CustomUser(List<LoginDTO> loginDTO) {

        super(loginDTO.get(0).getUsername(), loginDTO.get(0).getPassword(), getGrantedAuthoritiesList(loginDTO));
        this.userId = loginDTO.get(0).getUserId();
        this.name = loginDTO.get(0).getFirstname();
        this.farazKey = loginDTO.get(0).getFarazKey();
        this.companyId = loginDTO.get(0).getCompanyId();
        this.personId = loginDTO.get(0).getPersonId();
    }

    public static Set<GrantedAuthority> getGrantedAuthoritiesList(List<LoginDTO> loginDTOList) {
        Set<GrantedAuthority> grantedAuthoritiesList = new HashSet<>();
        for (LoginDTO loginDTO : loginDTOList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                    loginDTO.getRoleTitle()
            );
            grantedAuthoritiesList.add(grantedAuthority);
        }
        System.out.println("in getGrantedAuthoritiesList");
        return grantedAuthoritiesList;
    }

}
