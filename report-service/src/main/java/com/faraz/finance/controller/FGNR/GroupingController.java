package com.faraz.finance.controller.FGNR;

import com.faraz.finance.controller.FGNR.dto.GroupingDTO;
import com.faraz.finance.model.FGNR.Grouping;
import com.faraz.finance.service.FGNR.GroupingService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("grouping")
@RequiredArgsConstructor
public class GroupingController {

    private final JwtUtil jwtUtil;
    private final GroupingService groupingService;

    @PutMapping
    public ResponseEntity<GroupingDTO> update(@RequestBody @Valid GroupingDTO dto,
                                              HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        Grouping grouping = groupingService.update(dto.toModel(companyId));
        return ResponseEntity.ok(GroupingDTO.fromModel(grouping));
    }

    @PostMapping
    public ResponseEntity<GroupingDTO> create(@RequestBody @Valid GroupingDTO dto,
                                                         HttpServletRequest request) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        Grouping grouping = groupingService.create(dto.toModel(companyId));
        return ResponseEntity.ok(GroupingDTO.fromModel(grouping));
    }

    @GetMapping
    public ResponseEntity<List<Grouping>> getAll(HttpServletRequest request, @RequestParam("categoryTypeid") Long categoryTypeId) {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);

        return ResponseEntity.ok(groupingService.getAll(companyId, categoryTypeId).get());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        return ResponseEntity.ok(groupingService.delete(id));
    }



}
