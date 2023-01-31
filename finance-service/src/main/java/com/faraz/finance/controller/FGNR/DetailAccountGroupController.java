package com.faraz.finance.controller.FGNR;


import com.faraz.finance.model.FGNR.Company;
import com.faraz.finance.model.FGNR.DetailAccountGroup;
import com.faraz.finance.service.FGNR.DetailAccountGroupService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("detail-account-group")
@RequiredArgsConstructor
public class DetailAccountGroupController {

    private final JwtUtil jwtUtil;
    private final DetailAccountGroupService detailAccountGroupService;

    @GetMapping
    public ResponseEntity<Set<DetailAccountGroup>> getAll(HttpServletRequest request) {

        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        return ResponseEntity.ok(detailAccountGroupService.getAll(companyId));
    }

    @DeleteMapping
    public ResponseEntity<DetailAccountGroup> delete(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(detailAccountGroupService.delete(id));
    }

    @GetMapping("by-id")
    public ResponseEntity<DetailAccountGroup> getOne(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(detailAccountGroupService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<DetailAccountGroup> create(@RequestBody @Valid DetailAccountGroup detailAccountGroup,
                                                     HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        detailAccountGroup.setCompanyId(Company.builder().id(companyId).build());
        return ResponseEntity.ok(detailAccountGroupService.create(detailAccountGroup));
    }

    @PutMapping
    public ResponseEntity<DetailAccountGroup> update(@RequestBody @Valid DetailAccountGroup detailAccountGroup) {
        return ResponseEntity.ok(detailAccountGroupService.update(detailAccountGroup));
    }
}
