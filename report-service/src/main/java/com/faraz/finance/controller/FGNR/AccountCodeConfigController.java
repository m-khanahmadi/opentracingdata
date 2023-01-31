package com.faraz.finance.controller.FGNR;


import com.faraz.finance.exception.ExpectedTokenValueException;
import com.faraz.finance.model.FGNR.AccountCodeConfig;
import com.faraz.finance.service.FGNR.AccountCodeConfigService;
import com.faraz.finance.tools.JwtUtil;
import lombok.RequiredArgsConstructor;
import io.opentracing.Tracer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("account-code-config")
@RequiredArgsConstructor
public class AccountCodeConfigController {

    private final JwtUtil jwtUtil;
    private final AccountCodeConfigService service;
    private final Tracer tracer;

    @GetMapping
    public ResponseEntity<AccountCodeConfig> getOne(HttpServletRequest request) throws ExpectedTokenValueException {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        return ResponseEntity.ok(service.getOne(null, companyId));
/*        otTracer.init();
        Span span = otTracer.tracer.spanBuilder("GetAccountCodeConfig").setSpanKind(Span.Kind.SERVER).startSpan();
        try (Scope scope = otTracer.tracer.withSpan(span)) {

            Meter meter = OpenTelemetry.getMeterProvider().get("Finance-app", "2.0");
            LongMeasure spanTimer =
                    meter
                            .longMeasureBuilder("spanTimer")
                            .setUnit("ms")
                            .setDescription("How long getOneAccountCode spans take")
                            .setAbsolute(true)
                            .build();
            span.setAttribute("companyID", companyId);
            span.setAttribute("AccountCodeConfigID", service.getOne(span, companyId).getId());
            span.addEvent("Event_getAccountCodeConfig");
            Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
            return ResponseEntity.ok(service.getOne(span, companyId));
        } catch (Throwable t) {
            Status status = Status.UNKNOWN.withDescription("Error in account-code-config getOne");
            span.setStatus(status);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } finally {
            span.end(); // closing the scope does not end the span, this has to be done manually
        }

 */
    }

    @PutMapping
    public ResponseEntity<AccountCodeConfig> update(@RequestBody AccountCodeConfig codeConfig) {
        return ResponseEntity.ok(service.update(codeConfig));
    }
    @PostMapping
    public ResponseEntity<AccountCodeConfig> createCode(HttpServletRequest request, @RequestParam String code) throws ExpectedTokenValueException {
       // service.
        return update(service.getOne(null, 1));
    }

}
