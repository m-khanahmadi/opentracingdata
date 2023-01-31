package com.faraz.finance.repository.FSEC;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final EntityManager entityManager;

    @Override
    public void deActiveUserByPersonId(Long id) {
        entityManager.joinTransaction();
        Session session = (Session) entityManager.getDelegate();
        String sql = "UPDATE FSEC.USERS SET ISACTIVE=0 WHERE PERSONID =" + id;

        session.createSQLQuery(sql).executeUpdate();

    }
}
