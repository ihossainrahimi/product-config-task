package review.userproduct.application.web;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.userproduct.domain.entity.UserProduct.Status;
import review.userproduct.application.service.UserProductService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static review.shared.web.UrlMappings.*;

@RestController
@RequiredArgsConstructor
public class UserProductController {

    private final UserProductService userProductService;


    @ResponseStatus(CREATED)
    @PostMapping(USER_PRODUCT)
    public void create(@RequestBody @Validated UserProductCreationDto dto) {
        userProductService.create(dto.toUserProduct());
    }

    @ResponseStatus(CREATED)
    @PatchMapping(UPDATE_USER_PRODUCT)
    public void update(@PathVariable Integer id, Status status) {
        userProductService.update(id, status);
    }

    @ResponseStatus(OK)
    @GetMapping(USER_PRODUCT)
    public Pagination<UserProductDto> getAll(Pageable pageable) {
        var products = userProductService.getAllByUserId(pageable);
        return products.map(UserProductDto::shortInfo);
    }

    @ResponseStatus(OK)
    @GetMapping(GET_USER_PRODUCT)
    public UserProductDto getDetail(@PathVariable Integer id) {
        var userProduct = userProductService.getById(id);
        return UserProductDto.of(userProduct);
    }

}
