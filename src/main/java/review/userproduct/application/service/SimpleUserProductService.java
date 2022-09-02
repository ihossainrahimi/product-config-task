package review.userproduct.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import review.enquiry.EnquiryService;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.userproduct.domain.entity.UserProduct;
import review.userproduct.domain.entity.UserProduct.Status;
import review.userproduct.domain.repository.UserProductRepository;

@Service
@RequiredArgsConstructor
public class SimpleUserProductService implements UserProductService {

    private final EnquiryService enquiryService;
    private final UserProductRepository userProductRepository;

    @Override
    public void create(UserProduct userProduct) {
        var amount = enquiryService.inquiryProductAmount(userProduct.getProductId());
        userProduct.setAmount(amount);
        userProductRepository.save(userProduct);
    }

    @Override
    public Pagination<UserProduct> getAllByUserId(Pageable pageable) {
        return userProductRepository.findAllByUserId(pageable);
    }

    @Override
    public UserProduct getById(Integer id) {
        return userProductRepository.findById(id).orElseThrow(UserProductNotFoundException::new);
    }

    @Override
    public void update(Integer id, Status status) {
        var userProduct = getById(id);
        userProduct.setStatus(status);
        userProductRepository.save(userProduct);
    }

    @Override
    public UserProduct getByUserIdAndProductIdWhereStatusIsApproved(Integer userId, Integer productId) {
        return userProductRepository.getByUserIdAndProductIdWhereStatusIsApproved(userId, productId).orElseThrow(UserProductNotFoundException::new);
    }
}
