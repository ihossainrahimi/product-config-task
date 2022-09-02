package review.user.application.web;

import review.user.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static lombok.AccessLevel.PROTECTED;

@JsonAutoDetect(fieldVisibility = ANY)
@AllArgsConstructor(access = PROTECTED)
public class UserDto {

    private Integer id;
    private String name;
    private String nationalCode;
    private String mobileNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserDto(Integer id, String name, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    static UserDto of(User user) {
        return new UserDto(user.getId(), user.getName(), user.getNationalCode(), user.getMobileNumber(),
                user.getCreatedAt(), user.getUpdatedAt());
    }

    static UserDto shortInfo(User user) {
        return new UserDto(user.getId(), user.getName(), user.getMobileNumber());
    }
}
