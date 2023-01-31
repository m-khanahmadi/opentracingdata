package com.faraz.finance.repository.FSYS.detailAccountGroupType;

import com.faraz.finance.model.FSYS.DetailAccountGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailAccountGroupTypeRepository extends JpaRepository<DetailAccountGroupType,Integer>,CustomDetailAccountGroupTypeRepository {
}

interface CustomDetailAccountGroupTypeRepository{

}