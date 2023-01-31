package com.faraz.finance.repository.FGNR.numberingMethodDetail;

import com.faraz.finance.model.FGNR.NumberingMethodDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NumberingMethodDetailRepository extends JpaRepository<NumberingMethodDetail, Integer>, CustomNumberingMethodDetailRepository {
    Optional<NumberingMethodDetail> findByNumberingMethodId_Id(Integer numberingMethodId);


}

interface CustomNumberingMethodDetailRepository {
    Optional<List<NumberingMethodDetail>> getAllByCompanyId(Integer companyId);
}