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
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("account-code-config")
@RequiredArgsConstructor
public class AccountCodeConfigController {

    private final JwtUtil jwtUtil;
    private final AccountCodeConfigService service;
    private final Tracer tracer;
    private final RestTemplate restTemplate;


    @GetMapping
    public ResponseEntity<AccountCodeConfig> getOne(HttpServletRequest request) throws ExpectedTokenValueException {
        Integer companyId = jwtUtil.getCompanyIdFromHeader(request);
        Enumeration<String> enums = request.getHeaderNames();
        HttpHeaders headers = new HttpHeaders();
        while(enums.hasMoreElements()) {
            String name = enums.nextElement();
            headers.add(name, request.getHeader(name));
            System.out.println("k------>" + name +"==="+request.getHeader(name));
        }
        headers.add("client-secret", "mantraERP12252***");
        headers.add("clientId", "secret");
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<AccountCodeConfig> responseEntity = restTemplate.exchange(
                    "http://192.168.1.5:8790/finance-business-service/account-code-config",
                    HttpMethod.GET, httpEntity, AccountCodeConfig.class);
            System.out.println("request9====:" + responseEntity.getBody().getLenLedger());
            System.out.println("request10====:" + responseEntity.getBody());
            return ResponseEntity.ok(responseEntity.getBody());
        }catch (Exception er)
        {
            System.out.println("errrrrrrrrrrrrr========"+er.toString());
            return ResponseEntity.ok(null);
        }
        //return ResponseEntity.ok(service.getOne(null, companyId));
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
        Enumeration<String> enums = request.getHeaderNames();
        HttpHeaders headers = new HttpHeaders();
        while (enums.hasMoreElements()) {
            String name = enums.nextElement();
            headers.add(name, request.getHeader(name));
            System.out.println("k------>" + name + "===" + request.getHeader(name));
        }
        headers.add("client-secret", "mantraERP12252***");
        headers.add("clientId", "secret");
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<AccountCodeConfig> responseEntity = restTemplate.exchange(
                    "http://192.168.1.5:8790/finance-business-service/account-code-config?code="+code,
                    HttpMethod.POST, httpEntity, AccountCodeConfig.class);
            System.out.println("request9====:" + responseEntity.getBody().getLenLedger());
            System.out.println("request10====:" + responseEntity.getBody());
            return ResponseEntity.ok(responseEntity.getBody());
        } catch (Exception er) {
            System.out.println("errrrrrrrrrrrrr========" + er.toString());
            return ResponseEntity.ok(null);
        }
    }
}
