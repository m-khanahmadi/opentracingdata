package com.faraz.finance.repository.FGNR.subLedgerDetail;

import com.faraz.finance.model.FGNR.SubsidiaryLedgerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubsidiaryLedgerDetailRepository extends JpaRepository<SubsidiaryLedgerDetail,Integer> {

//    Optional<List<SubsidiaryLedgerDetail>> findAllByIdAndCompanyId_IdAndIsDeletedIsFalse(Integer id,Integer companyId);

    @Query(value = "SELECT sdld.ID FROM FGNR.SUBSIDIARYLEDGERDETAIL sdld WHERE " +
            " sdld.SUBSIDIARYLEDGERID=:subLedgerId ", nativeQuery = true)
    Optional<List<Integer>> getSubLedgerDetailIdsBySubLedgerId(@Param("subLedgerId") Integer subLedgerId);

}
