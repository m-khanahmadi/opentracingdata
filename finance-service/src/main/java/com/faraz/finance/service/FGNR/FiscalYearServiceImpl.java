package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.model.FGNR.FiscalYear;
import com.faraz.finance.model.FGNR.Grouping;
import com.faraz.finance.repository.FGNR.fiscalYear.FiscalYearRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FiscalYearServiceImpl implements FiscalYearService {

    private final FiscalYearRepository fiscalYearRepository;

    @Override
    public FiscalYear create(FiscalYear fiscalYear) {
        FiscalYear entity;
        try {
            entity = fiscalYearRepository.save(fiscalYear);
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("Error in Create FiscalYear for companyid= {}", fiscalYear.getCompany().getId());
            return  null;
        }
        return entity;
    }

    @Override
    public FiscalYear update(FiscalYear fiscalYear) {
        if (fiscalYear.getId() == null || fiscalYear.getId() == 0)
            throw new ClientException("error.fiscalYear.not.found", String.valueOf(fiscalYear.getId()));

        Optional<FiscalYear> entity = fiscalYearRepository.findById(fiscalYear.getId());
        if (entity.isEmpty())
            throw new ClientException("error.fiscalYear.not.found", String.valueOf(fiscalYear.getId()));
        if (entity.get().getCompany().getId() != fiscalYear.getCompany().getId())
            throw new ClientException("error.fiscalYear.hasSecurity.error", String.valueOf(fiscalYear.getId()));

        return fiscalYearRepository.save(fiscalYear);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Optional<FiscalYear> entity = fiscalYearRepository.findById(id);
            if (entity.isEmpty())
                throw new ClientException("error.fiscalYear.not.found", String.valueOf(id));
            fiscalYearRepository.delete(entity.get());
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("FiscalYear by id={} could not be delete", id);
            return false;
        }
        return true;
    }

    @Override
    public Optional<List<FiscalYear>> getAll(Integer companyID) {
        return  fiscalYearRepository.findAllByCompany(companyID);
    }
}
