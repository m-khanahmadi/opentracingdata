package com.faraz.finance.repository.FGNR.classificationValue;

import com.faraz.finance.model.FGNR.ClassificationValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassificationValueRepository extends JpaRepository<ClassificationValue, Long> {

    Optional<List<ClassificationValue>> findAllByCategorytypeAndCompany(Long categoryTypeId, int companyId);
}
