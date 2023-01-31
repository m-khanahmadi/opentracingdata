package com.faraz.finance.repository.FGNR.detailAccountBank;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.DetailAccountBank;
import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

@RequiredArgsConstructor
public class DetailAccountBankRepositoryCustomImpl implements DetailAccountBankRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DetailAccountBank create(DetailAccountBank detailAccountBank) {
        entityManager.joinTransaction();
        try {
            entityManager.persist(detailAccountBank);

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
        return detailAccountBank;
    }
}
