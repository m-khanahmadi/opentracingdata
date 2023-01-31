package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.exception.ServerException;
import com.faraz.finance.model.FGNR.NumberingMethod;
import com.faraz.finance.model.FGNR.NumberingMethodDetail;
import com.faraz.finance.repository.FGNR.numberingMethod.NumberingMethodRepository;
import com.faraz.finance.repository.FGNR.numberingMethodDetail.NumberingMethodDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NumberingMethodServiceImpl implements NumberingMethodService {

    private final NumberingMethodRepository numberingMethodRepository;
    private final NumberingMethodDetailRepository numberingMethodDetailRepository;


    @Override
    public List<NumberingMethod> getAll(Integer companyId) {
        Optional<List<NumberingMethod>> optionalNumberingMethodList = numberingMethodRepository.getAll(companyId);
        return optionalNumberingMethodList.orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public NumberingMethod create(NumberingMethod numberingMethod) {

        return numberingMethodRepository.create(numberingMethod);
    }

    @Override
    public NumberingMethod delete(Integer id) {
        Optional<NumberingMethod> optionalNumberingMethod = numberingMethodRepository.findById(id);
        if (optionalNumberingMethod.isEmpty()) {
            throw new ClientException("error.numbering.method.not.found", String.valueOf(id));
        }
        Optional<NumberingMethodDetail> optionalNumberingMethodDetail =
                numberingMethodDetailRepository.findByNumberingMethodId_Id(id);
        if (optionalNumberingMethodDetail.isPresent()) {
            throw new ClientException("error.delete.numbering.method", "Has Numbering Method Detail");
        }
        try {
            numberingMethodRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("error.internal.server");
        }
        return optionalNumberingMethod.get();
    }
}
