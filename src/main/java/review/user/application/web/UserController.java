package review.user.application.web;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import review.shared.domain.model.Pageable;
import review.shared.domain.model.Pagination;
import review.shared.web.UrlMappings;
import review.user.application.service.UserService;

import static org.springframework.http.HttpStatus.*;
import static review.shared.web.UrlMappings.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(USER)
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Validated UserCreationDto dto) {
        userService.create(dto.toUser());
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping(UPDATE_USER)
    public void update(@PathVariable Integer id, @RequestBody @Validated UserUpdateDto dto) {
        userService.update(id, dto.toUser());
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(DELETE_USER)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }


    @ResponseStatus(OK)
    @GetMapping(USER)
    public Pagination<UserDto> getAll(Pageable pageable) {
        var users = userService.getAll(pageable);
        return users.map(UserDto::shortInfo);
    }

    @ResponseStatus(OK)
    @GetMapping(UrlMappings.GET_USER)
    public UserDto getDetail(@PathVariable Integer id) {
        var user = userService.getById(id);
        return UserDto.of(user);
    }
}
