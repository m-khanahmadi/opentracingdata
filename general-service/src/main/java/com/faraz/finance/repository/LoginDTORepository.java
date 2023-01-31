package com.faraz.finance.repository;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.faraz.finance.controller.FGNR.dto.LoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
@JsonSerialize
@JsonNaming
@JsonPOJOBuilder
public interface LoginDTORepository extends JpaRepository<LoginDTO, Long> {
    @Query(" select new LoginDTO(u.id ,u.companyId.id,u.name,u.password," +
            " rp.permissionId.enTitle,u.personId.name,u.personId.family,u.personId.id," +
            "u.personId.personnelCode,u.companyId.companyKeyId.farazKey) " +
            " from User u inner join PersonPost pp on u.personId.id= pp.personId.id inner join" +
            " OrgChartRole ocr on pp.orgChartId.id=ocr.orgChartId.id inner join RolePermission rp " +
            " on ocr.roleId.id= rp.roleId.id where u.name=:username and u.isActive=true" +
            " and u.personId.isDeleted=false and u.companyId.companyKeyId.farazKey=:farazKey " +
            " and pp.isDeleted=false ")
    Optional<Collection<LoginDTO>> getRoleForLogin(@Param("username") String username, @Param("farazKey") int farazKey);
}
