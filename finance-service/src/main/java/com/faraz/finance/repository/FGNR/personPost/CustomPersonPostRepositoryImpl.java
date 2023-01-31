package com.faraz.finance.repository.FGNR.personPost;

import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomPersonPostRepositoryImpl implements CustomPersonPostRepository {

    private final EntityManager entityManager;

    @Override
    public void deletePrevPosts(Long personId, List<Short> unAssignOrganChartIsList) {
        Session session = (Session) entityManager.getDelegate();
        String sql = "UPDATE FGNR.PERSONPOST SET ISDELETED=1 WHERE PERSONID =" + personId +
                " AND ORGCHARTID IN (" + StringUtil.removeBracketFromArray(unAssignOrganChartIsList) + ")" ;

        session.createSQLQuery(sql).executeUpdate();


    }

    @Override
    public void deletePrevPosts(Long personId) {
        entityManager.joinTransaction();
        Session session = (Session) entityManager.getDelegate();
        String sql = "UPDATE FGNR.PERSONPOST SET ISDELETED=1 WHERE PERSONID =" + personId;

        session.createSQLQuery(sql).executeUpdate();
    }

}