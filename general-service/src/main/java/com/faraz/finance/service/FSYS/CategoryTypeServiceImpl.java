package com.faraz.finance.service.FSYS;

import com.faraz.finance.model.FSYS.CategoryType;
import com.faraz.finance.repository.FSYS.CategoryTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
public class CategoryTypeServiceImpl implements CategoryTypeService {

    private final CategoryTypeRepository categoryTypeRepository;

    @Override
    public List<CategoryType> getAll() {
        return categoryTypeRepository.findAll();
    }
}
