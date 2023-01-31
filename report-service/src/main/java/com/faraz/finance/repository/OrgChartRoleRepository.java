package com.faraz.finance.repository;

import com.faraz.finance.model.FGNR.OrgChart;
import com.faraz.finance.model.FGNR.OrgChartRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgChartRoleRepository extends JpaRepository<OrgChartRole, Short> {
//    Optional<Collection<OrgChartRole>> findByOrgChartId(OrgChart orgChartId);

    List<OrgChartRole> findByOrgChartId(OrgChart orgChart);
}
