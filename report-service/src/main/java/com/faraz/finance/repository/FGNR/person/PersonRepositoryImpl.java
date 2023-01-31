package com.faraz.finance.repository.FGNR.person;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements CustomPersonRepository {

    private final EntityManager entityManager;

    @Override
    public void makePersonDeleted(Long id) {
        entityManager.joinTransaction();
        Session session = (Session) entityManager.getDelegate();
        String sql = "UPDATE FGNR.PERSON SET ISDELETED=1 WHERE ID =" + id;
        session.createSQLQuery(sql).executeUpdate();
    }
}
