package io.github.supplygo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.core.HeaderFilters;
import org.zalando.logbook.core.QueryFilters;
import org.zalando.logbook.json.JsonHttpLogFormatter;

@Configuration(proxyBeanMethods=false)
public class LogBookConfig {

    @Bean
    public Logbook logbook() {

        ObjectMapper prettyObjectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);

        return Logbook.builder()
                .headerFilter(HeaderFilters.defaultValue())
                .queryFilter(QueryFilters.defaultValue())
                .sink(new DefaultSink(new JsonHttpLogFormatter(prettyObjectMapper), new DefaultHttpLogWriter()))
                .build();
    }

}
