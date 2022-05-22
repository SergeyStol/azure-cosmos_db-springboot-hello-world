package io.sstol.cosmosdb.common.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergey Stol
 * 2022-05-21
 */
@Configuration
public class ModelMapperConfiguration {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}