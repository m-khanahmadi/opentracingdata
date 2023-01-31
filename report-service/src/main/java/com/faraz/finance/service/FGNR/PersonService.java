package com.faraz.finance.service.FGNR;

import com.faraz.finance.controller.FGNR.dto.CreatePersonDTO;
import com.faraz.finance.controller.FGNR.dto.UpdatePersonDTO;
import com.faraz.finance.model.FGNR.Person;

import java.util.List;

public interface PersonService {
    Person create(CreatePersonDTO dto);

    List<Person> findAll(Integer companyId);

    Person update(UpdatePersonDTO dto);

    Person getOne(Long id);

    Person delete(Long id);
}
