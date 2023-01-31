package com.faraz.finance.controller.FGNR;

import com.faraz.finance.controller.FGNR.dto.ClassificationValueDTO;
import com.faraz.finance.model.FGNR.ClassificationValue;
import com.faraz.finance.service.FGNR.ClassificationValueService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("classificationValue")
@RequiredArgsConstructor
public class ClassificationValueController {
    private final JwtUtil jwtUtil;
    private final ClassificationValueService classificationValueService;

    @PutMapping
    public ResponseEntity<ClassificationValueDTO> update(@RequestBody @Valid ClassificationValueDTO dto,
                                                         HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        ClassificationValue classificationValue = classificationValueService.update(dto.toModel(companyId));
        return ResponseEntity.ok(ClassificationValueDTO.fromModel(classificationValue));
    }

    @PostMapping
    public ResponseEntity<ClassificationValueDTO> create(@RequestBody @Valid ClassificationValueDTO dto,
                                            HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        ClassificationValue classificationValue = classificationValueService.create(dto.toModel(companyId));
        return ResponseEntity.ok(ClassificationValueDTO.fromModel(classificationValue));
    }

    @GetMapping
    public ResponseEntity<List<ClassificationValue>> getAll(HttpServletRequest request, @RequestParam("categoryTypeid") Long categoryTypeId) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);

        return ResponseEntity.ok(classificationValueService.getAll(companyId, categoryTypeId).get());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok(classificationValueService.delete(id));
    }



}
