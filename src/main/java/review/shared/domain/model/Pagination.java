package review.shared.domain.model;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public record Pagination<T>(long total, boolean hasNext, boolean hasPrevious, List<T> elements) {

    public <R> Pagination<R> map(Function<? super T, R> mapper) {
        return new Pagination<>(total, hasNext, hasPrevious, getConvertedContent(mapper));
    }

    private <R> List<? extends R> getConvertedContent(Function<? super T, R> mapper) {
        Objects.requireNonNull(mapper, "Function must not be null!");

        return elements.stream().map(mapper).toList();
    }
}
