package com.faraz.finance.service.FGNR;

import com.faraz.finance.exception.ClientException;
import com.faraz.finance.model.FGNR.AccountCodeConfig;
import com.faraz.finance.repository.FGNR.AccountCodeConfigRepository;
import com.faraz.finance.tools.OpenTelemetryTracer;
import io.opentracing.Span;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCodeConfigServiceImpl implements AccountCodeConfigService {

    private final AccountCodeConfigRepository repository;
    private final OpenTelemetryTracer otTracer;

    @Override
    public AccountCodeConfig getOne(Span span, Integer companyId) {
//        Span spn = otTracer.tracer.spanBuilder("GetAccountCodeConfig")
//                .setSpanKind(Span.Kind.SERVER).setParent(span).stapaprtSpan();
//        spn.setAttribute("call Backend", companyId);
//        spn.end();
        Optional<AccountCodeConfig> optionalAccountCodeConfig = repository.findByCompanyId_Id(companyId);

        if (optionalAccountCodeConfig.isEmpty()) {
            throw new ClientException("error.account.code.config.not.found");
        }
        return optionalAccountCodeConfig.get();
    }

    @Override
    public AccountCodeConfig update(AccountCodeConfig codeConfig) {
        Optional<AccountCodeConfig> optionalAccountCodeConfig = repository.findById(codeConfig.getId());
        if (optionalAccountCodeConfig.isEmpty()) {
            throw new ClientException("error.account.code.config.not.found");
        }
        if (codeConfig.getLenLedger() <= codeConfig.getLenGroup()) {
            throw new ClientException("error.ledger.code.length.smaller.than.group");
        }
        if (codeConfig.getLenSubsidiary() <= codeConfig.getLenLedger()) {
            throw new ClientException("error.subsidiaryLedger.code.length.smaller.than.ledger");
        }
        return repository.save(codeConfig);
    }
}
