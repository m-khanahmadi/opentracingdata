package com.faraz.finance.controller.FGNR;


import com.faraz.finance.controller.FGNR.dto.DetailAccountCrateDTO;
import com.faraz.finance.model.FGNR.DetailAccount;
import com.faraz.finance.service.FGNR.DetailAccountService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("detail-account")
@RequiredArgsConstructor
public class DetailAccountController {

    private final DetailAccountService detailAccountService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<DetailAccount> create(@RequestBody @Valid DetailAccountCrateDTO detailAccountCrateDTO, HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        detailAccountCrateDTO.setCompanyId(companyId);
        return ResponseEntity.ok(detailAccountService.create(detailAccountCrateDTO));
    }
}
