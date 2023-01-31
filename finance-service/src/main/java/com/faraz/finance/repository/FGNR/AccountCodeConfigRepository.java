package com.faraz.finance.repository.FGNR;

import com.faraz.finance.model.FGNR.AccountCodeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCodeConfigRepository extends JpaRepository<AccountCodeConfig, Integer> {

    Optional<AccountCodeConfig> findByCompanyId_Id(Integer companyId);
}
