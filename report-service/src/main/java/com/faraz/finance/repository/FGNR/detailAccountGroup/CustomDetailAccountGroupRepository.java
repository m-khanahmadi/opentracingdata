package com.faraz.finance.repository.FGNR.detailAccountGroup;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CustomDetailAccountGroupRepository {
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer makeDetailAccountGroupDeleted(Integer id) {
        entityManager.joinTransaction();
        Session session = (Session) entityManager.getDelegate();
        String sql = "UPDATE FGNR.DETAILACCOUNTGROUP SET ISDELETED=1 WHERE ID = " + id;

        return session.createSQLQuery(sql).executeUpdate();
    }
}
