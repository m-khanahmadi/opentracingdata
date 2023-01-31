package com.faraz.finance.service.orgChart;

import com.faraz.finance.model.FGNR.OrgChart;

import java.util.List;
import java.util.Optional;

public interface OrgChartService {

    OrgChart create(OrgChart orgChart);

    Optional<List<OrgChart>> getAll(int companyId);
}
