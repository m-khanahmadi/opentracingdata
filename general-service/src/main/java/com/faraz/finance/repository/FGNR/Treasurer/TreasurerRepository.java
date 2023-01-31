package com.faraz.finance.repository.FGNR.Treasurer;

import com.faraz.finance.model.FGNR.Treasurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreasurerRepository extends JpaRepository<Treasurer,Integer>,CustomTreasurerRepository  {
}
interface CustomTreasurerRepository{
    Treasurer create(Treasurer treasurer);
}