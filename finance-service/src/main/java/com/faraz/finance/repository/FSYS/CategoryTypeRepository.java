package com.faraz.finance.repository.FSYS;

import com.faraz.finance.model.FSYS.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTypeRepository  extends JpaRepository<CategoryType, Long> {
}
