package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.DetailAccountGroup;
import com.faraz.finance.repository.FGNR.detailAccountGroup.CustomDetailAccountGroupRepository;
import com.faraz.finance.repository.FGNR.detailAccountGroup.DetailAccountGroupRepository;
import com.faraz.finance.tools.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DetailAccountGroupServiceImpl implements DetailAccountGroupService {

    private final DetailAccountGroupRepository detailAccountGroupRepository;
    private final CustomDetailAccountGroupRepository customRepository;

    @Override
    public Set<DetailAccountGroup> getAll(Integer companyId) {
        return detailAccountGroupRepository.getAllByCompanyId_IdAndIsDeletedIsFalse(companyId);
    }

    @Override
    public DetailAccountGroup delete(Integer id) {
        Optional<DetailAccountGroup> optionalDetailAccountGroup = detailAccountGroupRepository.findByIdAndIsDeletedIsFalse(id);
        if (optionalDetailAccountGroup.isEmpty()) {
            throw new ClientException("error.detail.account.group.not.found", String.valueOf(id));
        }
        Integer countOutput = customRepository.makeDetailAccountGroupDeleted(id);
        if (countOutput > 0) {
            return optionalDetailAccountGroup.get();
        }
        throw new ServerException("error.internal.server");
    }

    @Override
    public DetailAccountGroup getOne(Integer id) {
        Optional<DetailAccountGroup> optionalDetailAccountGroup = detailAccountGroupRepository.findByIdAndIsDeletedIsFalse(id);
        if (optionalDetailAccountGroup.isEmpty()) {
            throw new ClientException("error.detail.account.group.not.found", String.valueOf(id));
        }
        return optionalDetailAccountGroup.get();
    }

    @Override
    public DetailAccountGroup create(DetailAccountGroup detailAccountGroup) {
        try {
            detailAccountGroupRepository.save(detailAccountGroup);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ClientException("error.unique.constrains.violation", StringUtil.getUniquePropertyFromException(e));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("error.internal.server");
        }
        return detailAccountGroup;
    }

    @Override
    public DetailAccountGroup update(DetailAccountGroup detailAccountGroup) {
        if (detailAccountGroup.getId() == null) {
            throw new ClientException("error.property.null", "id");
        }
        Optional<DetailAccountGroup> optionalDetailAccountGroup = detailAccountGroupRepository.findByIdAndIsDeletedIsFalse(detailAccountGroup.getId());
        if (optionalDetailAccountGroup.isEmpty()) {
            throw new ClientException("error.detail.account.group.not.found", String.valueOf(detailAccountGroup.getId()));
        }
        return this.create(detailAccountGroup);
    }
}
