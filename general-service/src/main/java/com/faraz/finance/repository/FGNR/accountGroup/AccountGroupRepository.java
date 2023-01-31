package com.faraz.finance.repository.FGNR.accountGroup;

import com.faraz.finance.model.FGNR.AccountGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountGroupRepository extends JpaRepository<AccountGroup, Integer> {

    @Query(value = "SELECT * FROM FGNR.ACCOUNTGROUP AS ac  WHERE ac.COMPANYID=:companyId ORDER BY ID  DESC LIMIT 1", nativeQuery = true)
    Optional<AccountGroup> getLastRecord(@Param("companyId") Integer companyId);

    @Query(value = "UPDATE FGNR.ACCOUNTGROUP SET ISDELETED=1 WHERE ID =:id",nativeQuery = true)
    void makeAccountDeleted(Integer id);
}
