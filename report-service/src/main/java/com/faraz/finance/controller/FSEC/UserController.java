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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("createchart")
    public OrgChart createchart(@RequestParam String counter)
    {
        Optional<OrgChart> orgChart = orgChartRepository.findByTitle("faraz"+counter);


        if (!orgChart.isPresent()) {
            orgChart = Optional.of(OrgChart.builder()
                    .code("25"+counter)
                    .title("faraz"+counter)
                    .isDeleted(false)
                    .path("5-6")
                    .companyId(Company.builder().id(1).build())
                    .parentId(OrgChart.builder().id((short) 1).build())
                    .zoneId(Zone.builder().id((short) 1).build())
                    .branchId(Branch.builder().id(1).build())
                    .build());
            try {

                orgChartRepository.save(orgChart.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  orgChart.get();

    }

    @PostMapping("createuser")
    public User createuser(@RequestParam String counter, @RequestParam String nationalCode)
    {
        System.out.println("tttttttttttttt?>>>>>>="+counter+ "="+nationalCode);
        Optional<Person> personAdmin = personRepository.findByNationalCode(nationalCode);
        Optional<User> user = userRepository.findByName("Admin"+ counter);
        System.out.println("tttttttttttttt00000="+user);
        if (!user.isPresent()) {
            user = Optional.of(User.builder()
                    .name("Admin"+counter)
                    .companyId(Company.builder().id(1).build())
                    .password(passwordEncoder.encode("12252"))
                    .personId(personAdmin.get())
                    .isActive(true)
                    .build());
            try {

                userRepository.save(user.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  user.get();
    }

    @PostMapping("createuser_Old")
    public void createUser()
    {

        try {
            Optional<Role> role = roleRepository.findByTitle("ADMIN");
            if (!role.isPresent()) {
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
            Optional<Permission> permissionReadUser = permissionRepository.findByEnTitle("ROLE_USER_READ");
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
            List<RolePermission> rolePermissions = rolePermissionRepository.findAll();
            if (rolePermissions.isEmpty()) {
                RolePermission rolePermissionReadUser = RolePermission.builder()
                        .roleId(role.get())
                        .permissionId(permissionReadUser.get())
                        .build();

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

            Optional<OrgChart> orgChart = orgChartRepository.findByTitle("faraz");

            if (!orgChart.isPresent()) {
                orgChart = Optional.of(OrgChart.builder()
                        .code("25")
                        .title("faraz")
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
            }

            List<OrgChartRole> orgChartRole = orgChartRoleRepository.findByOrgChartId(orgChart.get());
            if (orgChartRole.isEmpty()) {
                orgChartRole = Collections.singletonList(OrgChartRole.builder()
                        .orgChartId(orgChart.get())
                        .roleId(role.get())
                        .build());
                try {

                    orgChartRoleRepository.save(orgChartRole.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Optional<Person> personAdmin = personRepository.findByNationalCode("4270041617");
            if (!personAdmin.isPresent()) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 1989);
                calendar.set(Calendar.MONTH, 6);
                calendar.set(Calendar.DAY_OF_MONTH, 22);
                personAdmin = Optional.of(Person.builder()
                        .birthDate(calendar.getTime())
                        .family("emami")
                        .name("mohsen")
                        .isDeleted(false)
                        .nationalCode("4270041617")
                        .personnelCode(12)
                        .build());
                try {

                    personRepository.save(personAdmin.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Optional<User> user = userRepository.findByName("Admin");
            if (!user.isPresent()) {
                user = Optional.of(User.builder()
                        .name("Admin")
                        .companyId(Company.builder().id(1).build())
                        .password(passwordEncoder.encode("12252"))
                        .personId(personAdmin.get())
                        .isActive(true)
                        .build());
                try {

                    userRepository.save(user.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Optional<PersonPost> personPost = personPostRepository.findByPersonId(personAdmin.get());
            if (!personPost.isPresent()) {
                personPost = Optional.of(PersonPost.builder()
                        .personId(personAdmin.get())
                        .orgChartId(orgChart.get())
                        .build());
                try {

                    personPostRepository.save(personPost.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
