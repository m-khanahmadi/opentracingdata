package com.faraz.finance.repository.FGNR.detailAccountBank;

import com.faraz.finance.model.FGNR.DetailAccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAccountBankRepository extends JpaRepository<DetailAccountBank,Integer> , DetailAccountBankRepositoryCustom {
}
interface DetailAccountBankRepositoryCustom {
    DetailAccountBank create(DetailAccountBank detailAccountBank);
}