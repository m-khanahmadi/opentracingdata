package com.faraz.finance.repository.FGNR.detailAccountAddress;

import com.faraz.finance.model.FGNR.DetailAccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAccountAddressRepositoryRepositoryCustom extends JpaRepository<DetailAccountAddress, Integer>, DetailAccountAddressRepositoryCustom {
}


interface DetailAccountAddressRepositoryCustom {

    void customSave(DetailAccountAddress detailAccountAddress);
}
