package com.faraz.finance.service.FSYS;

import com.faraz.finance.model.FSYS.CategoryType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryTypeService {

    public List<CategoryType> getAll();



}
