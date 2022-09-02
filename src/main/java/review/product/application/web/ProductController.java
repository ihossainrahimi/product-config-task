package review.product.application.web;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import review.comment.application.web.CommentDto;
import review.product.application.service.ProductService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static review.shared.web.UrlMappings.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(PRODUCT)
    @ResponseStatus(CREATED)
    public void create(@Valid @RequestBody ProductCreationDto dto) {
        productService.create(dto.toProduct());
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping(UPDATE_PRODUCT)
    public void update(@PathVariable Integer id, @RequestBody @Validated ProductUpdateDto dto) {
        productService.update(id, dto.toProduct());
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(DELETE_PRODUCT)
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }


    @ResponseStatus(OK)
    @GetMapping(PRODUCT)
    public List<ProductDto> getAll() {
        return productService.getAllWhereVisibleIsTrueAndDeletedAtIsFalse().stream().map(ProductDto::shortInfo).toList();
    }

    @ResponseStatus(OK)
    @GetMapping(GET_PRODUCT)
    public ProductDto getDetail(@PathVariable Integer id) {
        var product = productService.getById(id);
        var comments = productService.getLastThreeCommentsOfProduct(id).stream()
                .map(CommentDto::shortInfo).toList();
        var countOfComments = productService.getCountOfCommentsOfProduct(id);
        var averageScore = productService.getAverageScoreOfProduct(id);
        return ProductDto.of(product, comments, averageScore, countOfComments);
    }
}
