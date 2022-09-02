package review.shared.domain.model;

import lombok.Data;

@Data
public class Pageable {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort sort;
}
