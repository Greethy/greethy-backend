package com.greethy.core.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

@Configuration
public class ResourceConfig {

    @Bean
    public LocaleContextResolver localeContextResolver() {
        var resolver = new AcceptHeaderLocaleContextResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("vi"));
        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageResource = new ResourceBundleMessageSource();
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setBasenames("i18n.messages");
        messageResource.setUseCodeAsDefaultMessage(true);
        return messageResource;
    }

}
