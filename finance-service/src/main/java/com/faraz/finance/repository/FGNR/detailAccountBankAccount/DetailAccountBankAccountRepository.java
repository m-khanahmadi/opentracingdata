package com.faraz.finance.repository.FGNR.detailAccountBankAccount;

import com.faraz.finance.model.FGNR.DetailAccountBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAccountBankAccountRepository extends JpaRepository<DetailAccountBankAccount,Long>,CustomDetailAccountBankAccountRepository {
}

interface CustomDetailAccountBankAccountRepository{

    DetailAccountBankAccount create(DetailAccountBankAccount detailAccountBankAccount);
}