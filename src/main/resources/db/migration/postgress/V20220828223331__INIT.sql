CREATE SEQUENCE product_id_seq;
CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE user_product_id_seq;
CREATE SEQUENCE comment_id_seq;
CREATE SEQUENCE vote_id_seq;

CREATE TABLE product
(
    id                INTEGER PRIMARY KEY DEFAULT nextval('product_id_seq'),
    title             VARCHAR(255) NOT NULL,
    description       VARCHAR(255) NOT NULL,
    visible           BOOLEAN      NOT NULL,
    comment_authority VARCHAR(255) NOT NULL,
    vote_authority    VARCHAR(255) NOT NULL,
    created_at        TIMESTAMP    NOT NULL,
    updated_at        TIMESTAMP,
    deleted           BOOLEAN
);

CREATE TABLE "user"
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('user_id_seq'),
    name          VARCHAR(255),
    national_code CHAR(10),
    mobile_number CHAR(11),
    created_at    TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP
);

CREATE TABLE user_product
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('user_product_id_seq'),
    user_id    INTEGER      NOT NULL,
    product_id INTEGER      NOT NULL,
    amount     INT8,
    status     VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    CONSTRAINT fk_user_product_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_user_product_user FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE comment
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('comment_id_seq'),
    user_id     INTEGER      NOT NULL,
    product_id  INTEGER      NOT NULL,
    user_name   VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    state       VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    CONSTRAINT fk_comment_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES "user" (id)
);

CREATE TABLE vote
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('vote_id_seq'),
    user_id    INTEGER      NOT NULL,
    product_id INTEGER      NOT NULL,
    score      FLOAT        NOT NULL,
    state      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    CONSTRAINT fk_vote_product FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT fk_vote_user FOREIGN KEY (user_id) REFERENCES "user" (id)
);

ALTER SEQUENCE product_id_seq OWNED BY product.id;
ALTER SEQUENCE user_id_seq OWNED BY "user".id;
ALTER SEQUENCE user_product_id_seq OWNED BY user_product.id;
ALTER SEQUENCE comment_id_seq OWNED BY comment.id;
ALTER SEQUENCE vote_id_seq OWNED BY vote.id;

CREATE INDEX comment_created_at_index ON comment (created_at);
CREATE INDEX comment_state_index ON comment (state);
CREATE INDEX user_product_status_index ON user_product (status);