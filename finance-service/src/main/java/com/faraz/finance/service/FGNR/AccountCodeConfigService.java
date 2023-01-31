package com.faraz.finance.service.FGNR;

import com.faraz.finance.model.FGNR.AccountCodeConfig;
import io.opentracing.Span;

public interface AccountCodeConfigService {
    AccountCodeConfig getOne(Span span, Integer companyId);

    AccountCodeConfig update(AccountCodeConfig codeConfig);
}
