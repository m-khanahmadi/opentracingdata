package com.faraz.finance.repository.FGNR.subLeder;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CustomSubLedgerRepository {

    private final EntityManager entityManager;

    public void makeSubLedgerDeletedById(Integer id) {

        entityManager.joinTransaction();
        Session session = (Session) entityManager.getDelegate();
        String sql = "UPDATE FGNR.SUBSIDIARYLEDGER SET ISDELETED=1 WHERE ID = " + id;
        session.createSQLQuery(sql).executeUpdate();
    }
}
