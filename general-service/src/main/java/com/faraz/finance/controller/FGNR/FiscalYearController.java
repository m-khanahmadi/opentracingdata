package com.faraz.finance.controller.FGNR;


import com.faraz.finance.controller.FGNR.dto.FiscalYearDTO;
import com.faraz.finance.model.FGNR.FiscalYear;
import com.faraz.finance.service.FGNR.FiscalYearService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("fiscalYear")
@RequiredArgsConstructor
public class FiscalYearController {

    private final JwtUtil jwtUtil;
    private final FiscalYearService fiscalYearService;

    @PutMapping
    public ResponseEntity<FiscalYearDTO> update(@RequestBody @Valid FiscalYearDTO dto,
                                              HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        FiscalYear fiscalYear = fiscalYearService.update(dto.toModel(companyId));
        return ResponseEntity.ok(FiscalYearDTO.fromModel(fiscalYear));
    }

    @PostMapping
    public ResponseEntity<FiscalYearDTO> create(@RequestBody @Valid FiscalYearDTO dto,
                                              HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        FiscalYear fiscalYear = fiscalYearService.create(dto.toModel(companyId));
        return ResponseEntity.ok(FiscalYearDTO.fromModel(fiscalYear));
    }

    @GetMapping
    public ResponseEntity<List<FiscalYear>> getAll(HttpServletRequest request, @RequestParam("categoryTypeid") Long categoryTypeId) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);

        return ResponseEntity.ok(fiscalYearService.getAll(companyId).get());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok(fiscalYearService.delete(id));
    }



}
