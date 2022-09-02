package review.shared.web.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileNumberConstraintValidator implements ConstraintValidator<MobileNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isValidNationalCode(value);
    }

    private boolean isValidNationalCode(String value) {
        return StringUtils.hasText(value) && value.startsWith("09") && value.length() == 11;
    }
}
