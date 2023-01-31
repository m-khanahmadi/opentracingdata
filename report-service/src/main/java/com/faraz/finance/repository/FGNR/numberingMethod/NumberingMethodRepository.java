package com.faraz.finance.repository.FGNR.numberingMethod;

import com.faraz.finance.model.FGNR.NumberingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NumberingMethodRepository extends JpaRepository<NumberingMethod, Integer>, CustomNumberingMethodRepository {
}

interface CustomNumberingMethodRepository {
    Optional<List<NumberingMethod>> getAll(Integer companyId);

    NumberingMethod create(NumberingMethod numberingMethod);
}

