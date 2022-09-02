package review.springboot;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class I18nConfig {
    private static final String MESSAGE_SOURCE_PATH = "classpath:i18n/messages";

    @Bean
    public MessageSource messageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_PATH);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    MessageSourceAccessor defaultMessageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource, Locale.forLanguageTag("fa"));
    }
}
