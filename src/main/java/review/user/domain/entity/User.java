package review.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String nationalCode;
    private String mobileNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String name, String nationalCode, String mobileNumber) {
        this.name = name;
        this.nationalCode = nationalCode;
        this.mobileNumber = mobileNumber;
    }

    public void update(User user) {
        this.setName(user.getName());
        this.setNationalCode(user.getNationalCode());
        this.setMobileNumber(user.getMobileNumber());
    }
}
