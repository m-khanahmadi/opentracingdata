package com.faraz.finance.repository.FGNR.detailAccount;

import com.faraz.finance.model.FGNR.DetailAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAccountRepository extends JpaRepository<DetailAccount,Long> ,CustomDetailAccountRepository{

}

interface CustomDetailAccountRepository{
    DetailAccount create(DetailAccount detailAccount);
}