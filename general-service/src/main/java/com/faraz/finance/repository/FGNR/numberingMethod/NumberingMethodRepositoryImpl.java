package com.faraz.finance.repository.FGNR.numberingMethod;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.NumberingMethod;
import com.faraz.finance.model.FSYS.Form;
import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NumberingMethodRepositoryImpl implements CustomNumberingMethodRepository {
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public Optional<List<NumberingMethod>> getAll(Integer companyId) {
        final String sql = "SELECT nm.ID , nm.STARTNUMBER , f.FATITLE,f.ENTITLE,f.CODE ,f.ID " +
                " FROM FGNR.NUMBERINGMETHOD nm INNER JOIN FSYS.FORM f ON nm.FORMID = f.ID" +
                " WHERE nm.ISACTIVE = 1  AND nm.COMPANYID=" + companyId;

        return Optional.of(
                jdbcTemplate.query(sql, (rs, rowNum) -> NumberingMethod.builder()
                        .id(rs.getInt(1))
                        .startNumber(rs.getInt(rs.getInt(2)))
                        .formId(Form.builder()
                                .faTitle(rs.getString(3))
                                .enTitle(rs.getString(4))
                                .code(rs.getInt(5))
                                .id(rs.getInt(6))
                                .build())
                        .build())
        );


    }

    @Override
    public NumberingMethod create(NumberingMethod numberingMethod) {
        try {
            entityManager.persist(numberingMethod);
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
        return numberingMethod;
    }
}
