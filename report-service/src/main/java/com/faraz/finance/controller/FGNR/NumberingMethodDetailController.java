package com.faraz.finance.controller.FGNR;

import com.faraz.finance.model.FGNR.NumberingMethodDetail;
import com.faraz.finance.service.FGNR.NumberingMethodDetailService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("numbering-method-detail")
@RequiredArgsConstructor
public class NumberingMethodDetailController {

    private final NumberingMethodDetailService numberingMethodDetailService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<NumberingMethodDetail> create(@RequestBody @Valid NumberingMethodDetail numberingMethodDetail) {
        return ResponseEntity.ok(numberingMethodDetailService.create(numberingMethodDetail));

    }

    @GetMapping
    public ResponseEntity<List<NumberingMethodDetail>> getAll(HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        return ResponseEntity.ok(numberingMethodDetailService.getAll(companyId));
    }

    @GetMapping("by-id")
    public ResponseEntity<NumberingMethodDetail> getOne(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(numberingMethodDetailService.getOne(id));
    }

    @DeleteMapping
    public ResponseEntity<NumberingMethodDetail> delete(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(numberingMethodDetailService.delete(id));
    }

    @PutMapping
    public ResponseEntity<NumberingMethodDetail> update(@RequestBody NumberingMethodDetail numberingMethodDetail) {
        return this.create(numberingMethodDetail);
    }
}
