package com.faraz.finance.repository.FGNR.Treasurer;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.Treasurer;
import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@RequiredArgsConstructor
public class CustomTreasurerRepositoryImpl implements CustomTreasurerRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Treasurer create(Treasurer treasurer) {
        entityManager.joinTransaction();
        try {
            entityManager.persist(treasurer);

        } catch (PersistenceException e) {
            throw new ClientException("error.unique.constrains.violation", "FormId");
        } catch (DataIntegrityViolationException e) {
            throw new ClientException("error.unique.constrains.violation", StringUtil.getUniquePropertyFromException(e));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("error.internal.server");
        }
        return treasurer;
    }
}
