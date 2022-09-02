package review.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import review.comment.application.service.OnlyProductBuyersCouldCommentException;
import review.comment.application.service.ProductIsNotCommentable;
import review.shared.exception.AbstractNotFoundException;
import review.vote.application.service.OnlyProductBuyersCouldVoteException;
import review.vote.application.service.ProductIsNotVotable;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSourceAccessor messageSourceAccessor;

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorsDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new ArrayList<ErrorsDto.Error>();
        for (var fieldError : ex.getBindingResult().getFieldErrors()) {
            var message = getMessage(fieldError.getDefaultMessage());
            var errorCode = fieldError.getDefaultMessage();
            errors.add(new ErrorsDto.Error(errorCode, message));
        }
        return new ErrorsDto(errors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorsDto handleBindException(BindException exception) {
        var errors = new ArrayList<ErrorsDto.Error>();
        for (var fieldError : exception.getBindingResult().getFieldErrors()) {
            var message = getMessage(fieldError.getField() + "NotValid");
            var errorCode = fieldError.getField() + "NotValid";
            errors.add(new ErrorsDto.Error(errorCode, message));
        }
        return new ErrorsDto(errors);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(AbstractNotFoundException.class)
    public ErrorsDto handleAbstractNotFoundException(AbstractNotFoundException exception) {
        var errorCode = exception.getClass().getSimpleName();
        var message = getMessage(errorCode);
        return new ErrorsDto(List.of(new ErrorsDto.Error(errorCode, message)));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorsDto handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        var errorCode = exception.getClass().getSimpleName();
        var message = getMessage(errorCode);
        return new ErrorsDto(List.of(new ErrorsDto.Error(errorCode, message)));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorsDto handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        var errorCode = exception.getClass().getSimpleName();
        var message = getMessage(errorCode);
        return new ErrorsDto(List.of(new ErrorsDto.Error(errorCode, message)));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            ProductIsNotCommentable.class,
            OnlyProductBuyersCouldCommentException.class,
            OnlyProductBuyersCouldVoteException.class,
            ProductIsNotVotable.class
    })
    public ErrorsDto handleInternalServerErrorBySimpleName(Exception exception) {
        var errorCode = exception.getClass().getSimpleName();
        var message = getMessage(errorCode);
        return new ErrorsDto(List.of(new ErrorsDto.Error(errorCode, message)));
    }

    private String getMessage(String key) {
        try {
            return messageSourceAccessor.getMessage(key);
        } catch (NoSuchMessageException e) {
            return "";
        }
    }
}
