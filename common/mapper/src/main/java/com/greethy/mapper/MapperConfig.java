package com.greethy.mapper;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mapping configuration class for application settings.
 *
 * @author ThanhKien
 */
@Configuration
public class MapperConfig {

    /**
     * Creates and configures a ModelMapper for mapping object.
     *
     * @return An instance of {@code ModelMapper} configured
     * with skipNullEnabled set to true.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE)
                .setSkipNullEnabled(true);
        configureLocalDateMapping(mapper);
        return mapper;
    }

    /**
     * Configures the mapping between String and LocalDate in the given ModelMapper instance.
     * This method creates a type mapping for String to LocalDate, adds a converter for
     * parsing String to LocalDate using the "yyyy-MM-dd" date format, and sets a provider
     * for LocalDate instances.
     *
     * @param mapper The ModelMapper instance to configure.
     */
    private void configureLocalDateMapping(ModelMapper mapper) {
        mapper.createTypeMap(String.class, LocalDate.class);
        mapper.addConverter(new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        });
        mapper.getTypeMap(String.class, LocalDate.class).setProvider(new AbstractProvider<>() {
            @Override
            protected LocalDate get() {
                return LocalDate.now();
            }
        });
    }
}
