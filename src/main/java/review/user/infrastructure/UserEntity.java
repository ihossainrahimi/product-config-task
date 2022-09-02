package review.user.infrastructure;

import review.user.domain.entity.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "[user]")
@NoArgsConstructor(access = PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "bpchar")
    private String nationalCode;
    @Column(columnDefinition = "bpchar")
    private String mobileNumber;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserEntity(Integer id, String name, String nationalCode, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.nationalCode = nationalCode;
        this.mobileNumber = mobileNumber;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    static UserEntity of(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getNationalCode(), user.getMobileNumber());
    }

    User toUser() {
        return new User(id, name, nationalCode, mobileNumber, createdAt, updatedAt);
    }
}
