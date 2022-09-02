package review.user.application.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import review.shared.web.validator.MobileNumber;
import review.shared.web.validator.NationalCode;
import review.user.domain.entity.User;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public class UserCreationDto {

    @NotBlank(message = "UserNameIsRequired")
    private String name;
    @NationalCode(message = "UserNationalCodeIsNotValid")
    private String nationalCode;
    @MobileNumber(message = "UserMobileNumberIsNotValid")
    private String mobileNumber;

    User toUser() {
        return new User(name, nationalCode, mobileNumber);
    }
}
