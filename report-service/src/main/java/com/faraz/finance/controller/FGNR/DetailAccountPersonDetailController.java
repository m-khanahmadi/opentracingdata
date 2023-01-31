package com.faraz.finance.controller.FGNR;

import com.faraz.finance.controller.FGNR.dto.DetailAccountPersonDetailDTO;
import com.faraz.finance.model.FGNR.DetailAccountPersonDetail;
import com.faraz.finance.service.FGNR.DetailAccountPersonDetailService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("detailAccountPersonDetail")
@RequiredArgsConstructor
public class DetailAccountPersonDetailController {

    private final JwtUtil jwtUtil;
    private final DetailAccountPersonDetailService detailAccountPersonDetailService;

    @PutMapping
    public ResponseEntity<DetailAccountPersonDetailDTO> update(@RequestBody @Valid DetailAccountPersonDetailDTO dto,
                                              HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        DetailAccountPersonDetail detailAccountPersonDetail = detailAccountPersonDetailService.update(dto.toModel());
        return ResponseEntity.ok(DetailAccountPersonDetailDTO.fromModel(detailAccountPersonDetail));
    }

    @PostMapping
    public ResponseEntity<DetailAccountPersonDetailDTO> create(@RequestBody @Valid DetailAccountPersonDetailDTO dto,
                                              HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        DetailAccountPersonDetail detailAccountPersonDetail = detailAccountPersonDetailService.create(dto.toModel());
        return ResponseEntity.ok(DetailAccountPersonDetailDTO.fromModel(detailAccountPersonDetail));
    }

    @GetMapping
    public ResponseEntity<DetailAccountPersonDetail> getOne(HttpServletRequest request, @RequestParam("detailAccountid") Long detailAccountId,
                                                                  @RequestParam("classificationValueid") Long classificationValueId) {
        return ResponseEntity.ok(detailAccountPersonDetailService.getOne(detailAccountId, classificationValueId).get());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok(detailAccountPersonDetailService.delete(id));
    }

}
