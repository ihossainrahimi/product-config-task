package review.springboot;

import java.util.List;

public record ErrorsDto(List<Error> errors) {
    public record Error(String code, String message) {
    }
}
