package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.model.FGNR.ClassificationValue;
import com.faraz.finance.model.FGNR.Grouping;
import com.faraz.finance.repository.FGNR.grouping.GroupingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupingServiceImpl implements GroupingService {

    private final GroupingRepository groupingRepository;

    @Override
    public Grouping create(Grouping grouping) {
        Grouping entity;
        try {
            entity = groupingRepository.save(grouping);
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("Error in Create Grouping for companyid= {}", grouping.getCompany().getId());
            return  null;
        }
        return entity;
    }

    @Override
    public Grouping update(Grouping grouping) {
        if (grouping.getId() == null || grouping.getId() == 0)
            throw new ClientException("error.grouping.not.found", String.valueOf(grouping.getId()));

        Optional<Grouping> entity = groupingRepository.findById(grouping.getId());
        if (entity.isEmpty())
            throw new ClientException("error.grouping.not.found", String.valueOf(grouping.getId()));
        if (entity.get().getCompany().getId() != grouping.getCompany().getId())
            throw new ClientException("error.grouping.hasSecurity.error", String.valueOf(grouping.getId()));

        return groupingRepository.save(grouping);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Optional<Grouping> entity = groupingRepository.findById(id);
            if (entity.isEmpty())
                throw new ClientException("error.grouping.not.found", String.valueOf(id));
            entity.get().setIsDeleted(true);
            entity.get().setIsDefafult(false);
            groupingRepository.save(entity.get());
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("Grouping by id={} could not be delete", id);
            return false;
        }
        return true;
    }

    @Override
    public Boolean setDefault(Long id) {
        try {
            Optional<Grouping> entity = groupingRepository.findById(id);
            if (entity.isEmpty())
                throw new ClientException("error.grouping.not.found", String.valueOf(id));
            entity.get().setIsDefafult(true);
            groupingRepository.save(entity.get());
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("Grouping by id={} could not be delete", id);
            return false;
        }
        return true;
    }

    @Override
    public Optional<List<Grouping>> getAll(Integer companyID, Long categoryTypeID) {
        return groupingRepository.findAllByCategoryTypeAndCompany(categoryTypeID, companyID);
    }
}
