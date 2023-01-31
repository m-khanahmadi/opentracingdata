package com.faraz.finance.controller.FGNR;

import com.faraz.finance.controller.FGNR.dto.SubsidiaryLedgerDTO;
import com.faraz.finance.model.FGNR.SubsidiaryLedger;
import com.faraz.finance.service.FGNR.SubsidiaryLedgerService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("subsidiary-ledger")
@RequiredArgsConstructor
public class SubsidiaryLedgerController {

    private final SubsidiaryLedgerService service;
    private final JwtUtil jwtUtil;


    @PostMapping
    public ResponseEntity<SubsidiaryLedgerDTO> create(@RequestBody @Valid SubsidiaryLedgerDTO dto,
                                                      HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        SubsidiaryLedger subsidiaryLedger = service.create(dto.toModel(companyId));
        return ResponseEntity.ok(SubsidiaryLedgerDTO.fromModel(subsidiaryLedger));
    }

    @PutMapping
    public ResponseEntity<SubsidiaryLedgerDTO> update(@RequestBody @Valid SubsidiaryLedgerDTO dto,
                                                      HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        SubsidiaryLedger subsidiaryLedger = service.update(dto.toModel(companyId));
        return ResponseEntity.ok(SubsidiaryLedgerDTO.fromModel(subsidiaryLedger));

    }

    @DeleteMapping
    public ResponseEntity<SubsidiaryLedgerDTO> delete(@RequestParam("id") Integer id) {
        SubsidiaryLedger subsidiaryLedger = service.delete(id);
        return ResponseEntity.ok(SubsidiaryLedgerDTO.fromModel(subsidiaryLedger));
    }

    @GetMapping
    public ResponseEntity<List<SubsidiaryLedger>> getAll(HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        return ResponseEntity.ok(service.getAll(companyId));
    }

    @GetMapping("by-id")
    public ResponseEntity<SubsidiaryLedger> getById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
