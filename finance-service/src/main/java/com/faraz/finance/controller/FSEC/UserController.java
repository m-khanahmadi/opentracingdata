package com.faraz.finance.controller.FSEC;

import com.faraz.finance.controller.FGNR.dto.LoginDTO;
import com.faraz.finance.model.FGNR.*;
import com.faraz.finance.model.FSEC.User;
import com.faraz.finance.model.FSYS.Permission;
import com.faraz.finance.repository.*;
import com.faraz.finance.repository.FGNR.person.PersonRepository;
import com.faraz.finance.repository.FGNR.personPost.PersonPostRepository;
import com.faraz.finance.service.UserService;
import com.netflix.discovery.provider.Serializer;
import io.opentracing.Tracer;
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
import javax.swing.text.html.Option;
import java.util.*;


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
        System.out.println("aaaaaaaaaaaa="+username+"===="+farazKey);
        return userService.getLoginDto(username, farazKey);
    }

    @GetMapping("role-test")
    @PreAuthorize("hasRole('USER_WRITE')")
    public String test() {
        return "mohsen" ;
    }

    @PostMapping("createuser")
    public void createUser(@RequestParam String counter)
    {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.
                getRequestAttributes())).getRequest();
        System.out.println("errrrrrrrrrrrrr**********44444****************");
        int kk = 12/0;
        try {
            Optional<Role> role;
            Optional<Permission> permissionReadUser;
            Enumeration<String> enums = request.getHeaderNames();
            HttpHeaders headers = new HttpHeaders();
            while(enums.hasMoreElements()) {
                String name = enums.nextElement();
                headers.add(name, request.getHeader(name));
                System.out.println("k------>" + name +"==="+request.getHeader(name));
            }
            headers.add("client-secret", "mantraERP12252***");
            headers.add("clientId", "secret");
            HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
            try {
                System.out.println("errrrrrrrrrrrrr*ttttt*************************");
                ResponseEntity<Optional<Role>> responseEntity = restTemplate.exchange(
                        "http://192.168.1.5:8790/finance-business-service/user/getRole",
                        HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Optional<Role>>(){});
                System.out.println("request10====:" + responseEntity.getBody());
                role = responseEntity.getBody();
            }catch (Exception er)
            {
                System.out.println("errrrrrrrrrrrrr========"+er.toString());
                role = null;
            }
            System.out.println("errrrrrrrrrrrrr**************************");

            //Optional<Role> role = roleRepository.findByTitle("ADMIN");
            if (role == null || !role.isPresent()) {
                role = Optional.of(Role.builder()
                        .title("ADMIN")
                        .code("10")
                        .isDeleted(false)
                        .companyId(Company.builder().id(1).build())
                        .build());
                try {

                    roleRepository.save(role.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("errrrrrrrrrrrrr**************************1");
            try {
                ResponseEntity<Optional<Permission>> responseEntity = restTemplate.exchange(
                        "http://192.168.1.5:8790/finance-business-service/user/getPermission",
                        HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Optional<Permission>>(){});
                                System.out.println("request10====:" + responseEntity.getBody());
                permissionReadUser = responseEntity.getBody();
            }catch (Exception er)
            {
                System.out.println("errrrrrrrrrrrrr========"+er.toString());
                permissionReadUser = null;
            }
            System.out.println("errrrrrrrrrrrrr**************************2");

            //permissionReadUser = permissionRepository.findByEnTitle("ROLE_USER_READ");
            Optional<Permission> permissionWriteUser = permissionRepository.findByEnTitle("ROLE_USER_WRITE");
            if (!permissionReadUser.isPresent() && !permissionWriteUser.isPresent()) {
                permissionReadUser = Optional.of(Permission.builder()
                        .enTitle("ROLE_USER_READ")
                        .faTitle("خوانش کاربر")
                        .build());

                permissionWriteUser = Optional.of(Permission.builder()
                        .enTitle("ROLE_USER_WRITE")
                        .faTitle("ثبت کاربر")
                        .build());
                try {

                    permissionRepository.saveAll(Arrays.asList(permissionReadUser.get(), permissionWriteUser.get()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("errrrrrrrrrrrrr**************************3");

            List<RolePermission> rolePermissions = rolePermissionRepository.findAll();
            if (rolePermissions.isEmpty()) {
                RolePermission rolePermissionReadUser = RolePermission.builder()
                        .roleId(role.get())
                        .permissionId(permissionReadUser.get())
                        .build();
                System.out.println("errrrrrrrrrrrrr**************************4");

                RolePermission rolePermissionWriteUser = RolePermission.builder()
                        .roleId(role.get())
                        .permissionId(permissionWriteUser.get())
                        .build();
                try {
                    rolePermissionRepository.saveAll(Arrays.asList(rolePermissionReadUser,
                            rolePermissionWriteUser)
                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("errrrrrrrrrrrrr**************************5");

            Optional<OrgChart> orgChart = null;
            System.out.println("qqqqqqqqqqqqqq00000000000="+"faraz"+ counter);
            try {
            //     orgChart = orgChartRepository.findByTitle("faraz2");//+ counter);
            }catch (Exception err)
            {
                System.out.println("0000000000000000000000="+err.toString());
            }
            System.out.println("errrrrrrrrrrrrr**************************6");
            System.out.println("tttttttttttttt="+orgChart);

        //    if (!orgChart.isPresent()) {
                try {
                    ResponseEntity<OrgChart> responseEntity = restTemplate.exchange(
                            "http://192.168.1.5:8790/finance-business-service/user/createchart?counter="+counter,
                            HttpMethod.POST, httpEntity, OrgChart.class);
                    System.out.println("request10====:" + responseEntity.getBody());
                    System.out.println("errrrrrrrrrrrrr**************************7");
                    OrgChart org = responseEntity.getBody();
                    orgChart = Optional.ofNullable(org);
                  //  orgChartRepository.save(orgChart.get());
                }catch (Exception er)
                {
                    System.out.println("errrrrrrrrrrrrr========"+er.toString());
                    permissionReadUser = null;
                }
            System.out.println("errrrrrrrrrrrrr**************************9");

/*
                orgChart = Optional.of(OrgChart.builder()
                        .code("25"+counter)
                        .title("faraz"+counter)
                        .isDeleted(false)
                        .path("5-6")
                        .companyId(Company.builder().id(1).build())
                        .parentId(OrgChart.builder().id((short) 5).build())
                        .zoneId(Zone.builder().id((short) 1).build())
                        .build());
                try {

                    orgChartRepository.save(orgChart.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }

 */
        //    }

            List<OrgChartRole> orgChartRole = orgChartRoleRepository.findByOrgChartId(orgChart.get());
            if (orgChartRole.isEmpty()) {
                orgChartRole = Collections.singletonList(OrgChartRole.builder()
                        .orgChartId(orgChart.get())
                        .roleId(role.get())
                        .isDeleted(Boolean.FALSE)
                        .build());
                try {

                    orgChartRoleRepository.save(orgChartRole.get(0));
                    System.out.println("errrrrrrrrrrrrr**************************10");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("errrrrrrrrrrrrr**************************11");

            Optional<Person> personAdmin = personRepository.findByNationalCode("427004"+counter);
            if (!personAdmin.isPresent()) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 1989);
                calendar.set(Calendar.MONTH, 6);
                calendar.set(Calendar.DAY_OF_MONTH, 22);
                System.out.println("aaaaaaaaaaaaa+"+counter);
                personAdmin = Optional.of(Person.builder()
                        .birthDate(calendar.getTime())
                        .family("emami"+counter)
                        .name("mohsen")
                        .isDeleted(false)
                        .nationalCode(counter)
                        .personnelCode(Integer.getInteger(counter))
                        .build());
                try {
                    System.out.println("errrrrrrrrrrrrr**************************12");

                    personRepository.save(personAdmin.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("errrrrrrrrrrrrr**************************13");

            User user = null;//userRepository.findByName("Admin"+counter).get();
            try {
                ResponseEntity<User> responseEntity = restTemplate.exchange(
                        "http://192.168.1.5:8790/finance-business-service/user/createuser?counter="+counter+
                        "&nationalCode="+counter,
                        HttpMethod.POST, httpEntity, User.class);
                System.out.println("request4444410====:" + responseEntity.getBody());
                user = responseEntity.getBody();
            }catch (Exception er)
            {
                System.out.println("errrrrrr3412rrrrrrr========"+er.toString());
                user = null;
            }
            System.out.println("errrrrrrrrrrrrr**************************14");

            Optional<PersonPost> personPost = personPostRepository.findByPersonId(personAdmin.get());
            if (!personPost.isPresent()) {
                personPost = Optional.of(PersonPost.builder()
                        .personId(personAdmin.get())
                        .orgChartId(orgChart.get())
                        .isDeleted(Boolean.FALSE)
                        .build());
                try {

                    System.out.println("errrrrrrrrrrrrr**************************15");
                    personPostRepository.save(personPost.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("errrrrrrrrrrrrr**************************16");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
