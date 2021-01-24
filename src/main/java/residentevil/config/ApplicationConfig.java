package residentevil.config;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.annotation.PostConstruct;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.TimeZone;

@Configuration
@EnableCaching
public class ApplicationConfig {

    private static final String SYSTEM_TIME_ZONE = "UTC";

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(SYSTEM_TIME_ZONE));
    }

    @Bean
    ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        return modelMapper;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public Validator validator() {
        final String VALIDATION_MESSAGES_PROPERTIES = "languages/validation";

        return Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator(VALIDATION_MESSAGES_PROPERTIES)
                        )
                )
                .buildValidatorFactory()
                .getValidator();
    }
}
