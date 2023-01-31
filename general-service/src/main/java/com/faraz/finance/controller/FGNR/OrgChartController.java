package com.faraz.finance.controller.FGNR;

import com.faraz.finance.controller.FGNR.dto.OrgChartPostDTO;
import com.faraz.finance.exception.ClientException;
import com.faraz.finance.model.FGNR.OrgChart;
import com.faraz.finance.service.orgChart.OrgChartService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("organ-chart")
@RequiredArgsConstructor
public class OrgChartController {
    private final OrgChartService orgChartService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<OrgChartPostDTO> create(@RequestBody OrgChartPostDTO dto) {
        OrgChart orgChart = orgChartService.create(dto.toModel());
        return ResponseEntity.ok().body(dto.fromModel(orgChart));
    }

    @GetMapping
    public ResponseEntity<List<OrgChart>> getAll(HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        System.out.println("companyId " + companyId);
        Optional<List<OrgChart>> optionalOrgChartList = orgChartService.getAll(companyId);
        if (optionalOrgChartList.isEmpty()) {
            throw new ClientException("error.no.orgChart.found");
        }
        return ResponseEntity.ok().body(optionalOrgChartList.get());
    }


}
