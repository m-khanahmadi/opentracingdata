package com.faraz.finance.service;

import com.faraz.finance.model.FGNR.*;
import com.faraz.finance.model.FSEC.User;
import com.faraz.finance.model.FSYS.Permission;
import com.faraz.finance.repository.FGNR.person.PersonRepository;
import com.faraz.finance.repository.FGNR.personPost.PersonPostRepository;
import com.faraz.finance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostConstructService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final OrgChartRepository orgChartRepository;
    private final OrgChartRoleRepository orgChartRoleRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PersonPostRepository personPostRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void onInit() {
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
