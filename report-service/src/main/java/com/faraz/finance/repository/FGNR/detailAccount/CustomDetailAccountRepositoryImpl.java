package com.faraz.finance.repository.FGNR.detailAccount;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.DetailAccount;
import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@RequiredArgsConstructor
public class CustomDetailAccountRepositoryImpl implements CustomDetailAccountRepository {
    private final EntityManager entityManager;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DetailAccount create(DetailAccount detailAccount) {
        entityManager.joinTransaction();
        try {
            entityManager.persist(detailAccount);

        } catch (PersistenceException e) {
            throw new ClientException("error.unique.constrains.violation", "FormId");
        } catch (DataIntegrityViolationException e) {
            System.out.println(2);
            throw new ClientException("error.unique.constrains.violation", StringUtil.getUniquePropertyFromException(e));
        } catch (Exception e) {
            System.out.println(3);
            e.printStackTrace();
            throw new ServerException("error.internal.server");
        }
        return detailAccount;
    }
}
