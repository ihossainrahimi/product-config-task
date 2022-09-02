package review.enquiry;

import org.springframework.stereotype.Service;

@Service
public class MockEnquirySystem implements EnquiryService{

    @Override
    public Long inquiryProductAmount(Integer productId) {
        return 100000L;
    }
}
