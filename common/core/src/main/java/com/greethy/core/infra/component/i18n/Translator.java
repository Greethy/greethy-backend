package com.greethy.core.infra.component.i18n;

import com.greethy.core.infra.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class Translator {

    private final ResourceBundleMessageSource messageSource;

    public String getLocalizedMessage(String messageKeys, Object... params) {
        var locale = LocaleContextHolder.getLocale();
        return DataUtil.isNullOrEmpty(messageKeys) ? "" : messageSource.getMessage(messageKeys, params, locale);
    }

    public String getLocalizedMessage(String messageKeys) {
        return this.getLocalizedMessage(messageKeys, Collections.emptyList());
    }

}
