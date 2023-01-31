package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.model.FGNR.ClassificationValue;
import com.faraz.finance.repository.FGNR.classificationValue.ClassificationValueRepository;
import com.faraz.finance.repository.FSYS.CategoryTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ClassificationValueServiceImpl implements ClassificationValueService {
    private final ClassificationValueRepository classificationValueRepository;

    @Override
    public ClassificationValue create(ClassificationValue classificationValue) {
        ClassificationValue entity;
        try {
            entity = classificationValueRepository.save(classificationValue);
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("Error in Create ClassificationValue for companyid= {}", classificationValue.getCompany().getId());
            return  null;
        }
        return entity;
    }

    @Override
    public ClassificationValue update(ClassificationValue classificationValue) {
        if (classificationValue.getId() == null || classificationValue.getId() == 0)
            throw new ClientException("error.classificationValue.not.found", String.valueOf(classificationValue.getId()));

        Optional<ClassificationValue> entity = classificationValueRepository.findById(classificationValue.getId());
        if (entity.isEmpty())
            throw new ClientException("error.classificationValue.not.found", String.valueOf(classificationValue.getId()));
        if (entity.get().getCompany().getId() != classificationValue.getCompany().getId())
            throw new ClientException("error.classificationValue.hasSecurity.error", String.valueOf(classificationValue.getId()));

        return classificationValueRepository.save(classificationValue);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Optional<ClassificationValue> entity = classificationValueRepository.findById(id);
            if (entity.isEmpty())
                throw new ClientException("error.classificationValue.not.found", String.valueOf(id));
            entity.get().setIsDeleted(true);
            entity.get().setIsDefafult(false);
            classificationValueRepository.save(entity.get());
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("ClassificationValue by id={} could not be delete", id);
            return false;
        }
        return true;
    }

    @Override
    public Boolean setDefault(Long id) {
        try {
            Optional<ClassificationValue> entity = classificationValueRepository.findById(id);
            if (entity.isEmpty())
                throw new ClientException("error.classificationValue.not.found", String.valueOf(id));
            entity.get().setIsDefafult(true);
            classificationValueRepository.save(entity.get());
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("ClassificationValue by id={} could not be delete", id);
            return false;
        }
        return true;
    }

    @Override
    public Optional<List<ClassificationValue>> getAll(Integer companyID, Long categoryTypeID) {
        return classificationValueRepository.findAllByCategorytypeAndCompany(categoryTypeID, companyID);
    }
}
