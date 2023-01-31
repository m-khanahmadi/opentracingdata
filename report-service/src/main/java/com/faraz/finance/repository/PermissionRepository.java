package com.faraz.finance.repository;

import com.faraz.finance.model.FSYS.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByEnTitle(String enTitle);
}
