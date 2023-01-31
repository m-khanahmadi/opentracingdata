package com.faraz.finance.repository.FGNR.grouping;

import com.faraz.finance.model.FGNR.ClassificationValue;
import com.faraz.finance.model.FGNR.Grouping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupingRepository extends JpaRepository<Grouping, Long> {

    Optional<List<Grouping>> findAllByCategoryTypeAndCompany(Long categoryTypeId, int companyId);
}
