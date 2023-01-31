package com.faraz.finance.controller.FSEC;

import com.faraz.finance.controller.FGNR.dto.LoginDTO;
import com.faraz.finance.model.FGNR.OrgChart;
import com.faraz.finance.model.FGNR.Role;
import com.faraz.finance.model.FSEC.User;
import com.faraz.finance.model.FSYS.Permission;
import com.faraz.finance.repository.*;
import com.faraz.finance.repository.FGNR.person.PersonRepository;
import com.faraz.finance.repository.FGNR.personPost.PersonPostRepository;
import com.faraz.finance.service.UserService;
import com.netflix.discovery.provider.Serializer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Optional;


@RequestMapping("user")
@RestController
@RequiredArgsConstructor
@Serializer
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final OrgChartRepository orgChartRepository;
    private final OrgChartRoleRepository orgChartRoleRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PersonPostRepository personPostRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;



    @GetMapping("get-login-dto")
    public Collection<LoginDTO> getLoginDto(@RequestParam String username, @RequestParam int farazKey) {
    //    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>="+tracer);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                getRequestAttributes())).getRequest();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        System.out.println("url======"+request.getQueryString());
        System.out.println("url2======"+request.getRequestURL());
        Enumeration<String> enums = request.getHeaderNames();
        while(enums.hasMoreElements()) {
            String name = enums.nextElement();
            body.add(name, request.getHeader(name));
            System.out.println("k------>" + name +"==="+request.getHeader(name));
        }
        return userService.getLoginDto(username, farazKey);
    }

    @GetMapping("role-test")
    @PreAuthorize("hasRole('USER_WRITE')")
    public String test() {
        return "mohsen" ;
    }

    @GetMapping("getRole")
    public Optional<Role> getRole() {
        return roleRepository.findByTitle("ADMIN");
    }

    @GetMapping("getPermission")
    public Optional<Permission> getPermission()
    {
        return permissionRepository.findByEnTitle("ROLE_USER_READ");
    }

    @PostMapping("createchart")
    public OrgChart CreateChart(@RequestParam String counter)

    {
        System.out.println("jdjdjdjd="+counter);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                getRequestAttributes())).getRequest();
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> enums = request.getHeaderNames();
        while(enums.hasMoreElements()) {
            String name = enums.nextElement();
            headers.add(name, request.getHeader(name));
            System.out.println("k------>" + name +"==="+request.getHeader(name));
        }
        headers.add("client-secret", "mantraERP12252***");
        headers.add("clientId", "secret");
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<OrgChart> responseEntity = restTemplate.exchange(
                    "http://localhost:8790/report-service/user/createchart?counter="+counter,
                    HttpMethod.POST, httpEntity, OrgChart.class);
            System.out.println("request10====:" + responseEntity.getBody());
            OrgChart org = responseEntity.getBody();
            return org;
        }catch (Exception er)
        {
            System.out.println("errrrrrrrrrrrrr========"+er.toString());
            return null;
        }

    }

    @PostMapping("createuser")
    public User createuser(@RequestParam String counter,
                           @RequestParam String nationalCode)
    {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                getRequestAttributes())).getRequest();
        System.out.println(">>>>>>>>>>>>>>>>>>====:" + counter + "="+nationalCode);
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> enums = request.getHeaderNames();
        while(enums.hasMoreElements()) {
            String name = enums.nextElement();
            headers.add(name, request.getHeader(name));
            System.out.println("k------>" + name +"==="+request.getHeader(name));
        }
        headers.add("client-secret", "mantraERP12252***");
        headers.add("clientId", "secret");
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<User> responseEntity = restTemplate.exchange(
                    "http://localhost:8790/report-service/user/createuser?counter="+counter+
                    "&nationalCode="+nationalCode,
                    HttpMethod.POST, httpEntity, User.class);
            System.out.println("request110====:" + responseEntity.getBody());
            User user = responseEntity.getBody();
            return user;
        }catch (Exception er)
        {
            System.out.println("errrrrrrrr33rrrrr========"+er.toString());
            return null;
        }

    }
}