package com.faraz.finance.repository.FGNR.subLeder;

import com.faraz.finance.model.FGNR.SubsidiaryLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubsidiaryLedgerRepository extends JpaRepository<SubsidiaryLedger, Integer> {

    @Query(value = "SELECT sdl.ID FROM FGNR.SUBSIDIARYLEDGER sdl WHERE sdl.LEDGERID=:ledgerId " +
            " AND sdl.COMPANYID=:companyId AND sdl.ISDELETED=0 LIMIT 1", nativeQuery = true)
    Optional<SubsidiaryLedger> findByCompanyIdAndLedgerId(@Param("companyId") Integer companyId,
                                                          @Param("ledgerId") Integer ledgerId);

    Optional<SubsidiaryLedger> findByIdAndCompanyId_Id(Integer id, Integer companyId);


    Optional<List<SubsidiaryLedger>> findAllByCompanyId_Id(Integer companyId);

}
