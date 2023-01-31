package com.faraz.finance.repository.FGNR.detailAccountGroup;

import com.faraz.finance.model.FGNR.DetailAccountGroup;
import org.hibernate.result.UpdateCountOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DetailAccountGroupRepository extends JpaRepository<DetailAccountGroup, Integer> {

    Set<DetailAccountGroup> getAllByCompanyId_IdAndIsDeletedIsFalse(Integer company);
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Query(value = "UPDATE FGNR.DETAILACCOUNTGROUP SET ISDELETED=1 WHERE ID=?", nativeQuery = true)
    UpdateCountOutput makeDetailAccountGroupDeleted(@Param("id") Integer id);

    Optional<DetailAccountGroup> findByIdAndIsDeletedIsFalse(Integer id);
}
