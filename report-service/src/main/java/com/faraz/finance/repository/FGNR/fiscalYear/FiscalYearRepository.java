package com.faraz.finance.repository.FGNR.fiscalYear;


import com.faraz.finance.model.FGNR.FiscalYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FiscalYearRepository extends JpaRepository<FiscalYear, Long> {

    Optional<List<FiscalYear>> findAllByCompany(int companyId);

}
