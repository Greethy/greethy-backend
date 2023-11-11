package com.greethy.auth.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * Mapping configuration class for application settings.
 *
 * @author ThanhKien
 */
@Configuration
public class MapperConfig {

    /**
     * Creates and configures an ObjectMapper instance for JSON processing.
     *
     * @return An ObjectMapper customized configuration instance.
     */
    @Bean
    public ObjectMapper objectMapper() {
        var jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        var mapper = new ObjectMapper(jsonFactory);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        return mapper;
    }

    /**
     * Creates and configures a ModelMapper for mapping object.
     *
     * @return An instance of {@code ModelMapper} configured
     * with skipNullEnabled set to true.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}
