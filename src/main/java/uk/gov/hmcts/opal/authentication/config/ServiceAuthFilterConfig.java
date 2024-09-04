package uk.gov.hmcts.opal.authentication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.hmcts.reform.authorisation.filters.ServiceAuthFilter;
import uk.gov.hmcts.reform.authorisation.validators.ServiceAuthTokenValidator;

import java.util.List;

@Slf4j
@Configuration
public class ServiceAuthFilterConfig {

    private final ServiceAuthTokenValidator authTokenValidator;

    public ServiceAuthFilterConfig(ServiceAuthTokenValidator authTokenValidator) {
        this.authTokenValidator = authTokenValidator;
    }

    @Bean
    public ServiceAuthFilter serviceAuthFilter(
        @Value("${idam.s2s-authorised.services}") List<String> authorisedServices
    ) {
        log.info("Service to service authorised services: " + authorisedServices);
        return new ServiceAuthFilter(authTokenValidator, authorisedServices);
    }
}
