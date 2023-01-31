package com.faraz.finance.repository;


import com.faraz.finance.model.FGNR.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

}
