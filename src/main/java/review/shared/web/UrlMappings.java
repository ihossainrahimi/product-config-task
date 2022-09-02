package review.shared.web;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UrlMappings {

    public static final String PRODUCT = "/products";
    public static final String UPDATE_PRODUCT = PRODUCT + "/{id}";
    public static final String DELETE_PRODUCT = PRODUCT + "/{id}";
    public static final String GET_PRODUCT = PRODUCT + "/{id}";

    public static final String USER = "/users";
    public static final String UPDATE_USER = USER + "/{id}";
    public static final String DELETE_USER = USER + "/{id}";
    public static final String GET_USER = USER + "/{id}";

    public static final String USER_PRODUCT = "/user-products";
    public static final String UPDATE_USER_PRODUCT = USER_PRODUCT + "/{id}";
    public static final String GET_USER_PRODUCT = USER_PRODUCT + "/{id}";

    public static final String COMMENT = "/comments";
    public static final String GET_ALL_COMMENT = PRODUCT + "/{productId}" + COMMENT;
    public static final String UPDATE_COMMENT = COMMENT + "/{id}";
    public static final String DELETE_COMMENT = COMMENT + "/{id}";

    public static final String VOTE = "/votes";
    public static final String GET_ALL_VOTE = PRODUCT + "/{productId}" + VOTE;
    public static final String UPDATE_VOTE = VOTE + "/{id}";
    public static final String DELETE_VOTE = VOTE + "/{id}";
}
