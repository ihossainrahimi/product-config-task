package review.userproduct.infrastructure;

import lombok.NoArgsConstructor;
import review.product.infrastructure.ProductEntity;
import review.user.infrastructure.UserEntity;
import review.userproduct.domain.entity.UserProduct;
import review.userproduct.domain.entity.UserProduct.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static review.userproduct.domain.entity.UserProduct.Status.INITIAL;

@Entity(name = "user_product")
@NoArgsConstructor(access = PROTECTED)
public class UserProductEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Long amount;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", updatable = false, insertable = false)
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", updatable = false, insertable = false)
    private ProductEntity productEntity;

    public UserProductEntity(Integer id, Integer userId, Integer productId, Long amount, Status status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.status = status;
    }

    static UserProductEntity of(UserProduct userProduct) {
        return new UserProductEntity(userProduct.getId(), userProduct.getUserId(), userProduct.getProductId(),
                userProduct.getAmount(), userProduct.getStatus());
    }

    UserProduct toUserProduct() {
        return new UserProduct(id, userId, productId, amount, status, createdAt);
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        status = INITIAL;
    }
}
