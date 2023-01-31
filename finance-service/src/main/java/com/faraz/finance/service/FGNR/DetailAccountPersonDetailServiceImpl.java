package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.model.FGNR.DetailAccountPersonDetail;
import com.faraz.finance.model.FGNR.DetailAccountPersonDetail;
import com.faraz.finance.repository.FGNR.detailAccountPersonDetail.DetailAccountPersonDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class DetailAccountPersonDetailServiceImpl implements DetailAccountPersonDetailService {

    private final DetailAccountPersonDetailRepository detailAccountPersonDetailRepository;

    @Override
    public DetailAccountPersonDetail create(DetailAccountPersonDetail detailAccountPersonDetail) {
        DetailAccountPersonDetail entity;
        try {
            entity = detailAccountPersonDetailRepository.save(detailAccountPersonDetail);
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("Error in Create DetailAccountPersonDetail for detailAccount= {}", detailAccountPersonDetail.getDetailAccount().getId());
            return  null;
        }
        return entity;
    }

    @Override
    public DetailAccountPersonDetail update(DetailAccountPersonDetail detailAccountPersonDetail) {
        if (detailAccountPersonDetail.getId() == null || detailAccountPersonDetail.getId() == 0)
            throw new ClientException("error.detailAccountPersonDetail.not.found", String.valueOf(detailAccountPersonDetail.getId()));

        Optional<DetailAccountPersonDetail> entity = detailAccountPersonDetailRepository.findById(detailAccountPersonDetail.getId());
        if (entity.isEmpty())
            throw new ClientException("error.detailAccountPersonDetail.not.found", String.valueOf(detailAccountPersonDetail.getId()));

        return detailAccountPersonDetailRepository.save(detailAccountPersonDetail);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Optional<DetailAccountPersonDetail> entity = detailAccountPersonDetailRepository.findById(id);
            if (entity.isEmpty())
                throw new ClientException("error.detailAccountPersonDetail.not.found", String.valueOf(id));
            detailAccountPersonDetailRepository.delete(entity.get());
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error("DetailAccountPersonDetail by id={} could not be delete", id);
            return false;
        }
        return true;
    }

    @Override
    public Optional<DetailAccountPersonDetail> getOne(Long detailAccountID, Long classificationValueID) {
        return detailAccountPersonDetailRepository.findByDetailAccountAndClassificationValue(detailAccountID, classificationValueID);
    }
}
