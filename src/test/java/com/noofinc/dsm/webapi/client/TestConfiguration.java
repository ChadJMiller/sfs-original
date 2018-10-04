package com.noofinc.dsm.webapi.client;

import com.noofinc.dsm.webapi.client.core.DsmUrlProvider;
import com.noofinc.dsm.webapi.client.core.PropertiesDsmUrlProvider;
import com.noofinc.dsm.webapi.client.core.authentication.AuthenticationProvider;
import com.noofinc.dsm.webapi.client.core.authentication.PropertiesAuthenticationProvider;
import com.noofinc.dsm.webapi.client.core.timezone.PropertiesTimeZoneProvider;
import com.noofinc.dsm.webapi.client.core.timezone.TimeZoneProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import(DsmWebapiClientConfig.class)
public class TestConfiguration {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new PropertiesAuthenticationProvider();
    }

    @Bean
    public DsmUrlProvider urlProvider() {
        return new PropertiesDsmUrlProvider();
    }

    @Bean
    public TimeZoneProvider timeZoneProvider() {
        return new PropertiesTimeZoneProvider();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
