# Product Config Task

## Getting Started

Please follow these instructions to have SS up and running on your local machine for development and testing purposes.

### About

The application implements CRUD APIs for user, product, comment, vote, user-product(to buy a product).

- Product APIs
    - you could
        - create product,
        - make them visible or un visible,
        - add Limit on comments and votes,
        - get short info list of the products(only visible ones),
        - get detail of a specific product,
        - soft delete a product.

- User APIs
    - you could
        - creat user,
        - update information of user,
        - delete user,
        - get short info of all users,
        - get the detail of a specific user.

- User-Product APIs
    - you could
        - buy a product(it will be INITIAL state until someone approved or un approved it),
        - update the state of a purchase to APPROVED OR UN_APPROVED,
        - get all purchases of all users,
        - get details of a specific purchase.

- Comment APIs
    - you could
        - comment on product(it will be in INITIAL state until someone approved or un approved it),
        - hard delete a comment,
        - update the state of a comment to APPROVED or UN_APPROVED,
        - get All comments of a specific product.

- Vote APIs
    - you could
        - vote on a product(it will be in INITIAL until someone approved or un approved it),
        - hard delete a vote,
        - update the state of a vote to APPROVED or UN_APPROVED.

### Prerequisites

No dependencies are required other than JDK 17+, everything is integrated into the build toolchain.

### Development Profile

For development purposes, please activate the `dev` profile on your favorite IDE.

#### Intellij

If you're using Intellij, you can change your profile to `dev` by adding it in `active profile` section of
the `Run configuration`.

### Dependent Services

To fulfill its responsibilities, This project Server Service needs the following component:

- A Postgres instance to store its *to-be-persisted* data.

#### Run Dependent Services and Databases

On the `dev` profile, Product-Config-Task Server expects to find an Postgres instance listening on port `5432`. One way
to run
this dependency is to use the docker compose configurations in the [docker-compose](docker-compose.yml)
file. In order to do that just `up`:

```bash
docker-compose up
```

### Additional Configuration Files

If you're going to use additional configuration locations in addition to the default locations, specify that location
using the
`spring.config.additional-location` configuration property.

### Postgres Configurations

In order to have a working connection to Postgres, we should provide:

- `spring.datasource.url` Is the JDBC URL to Postgres instance with `jdbc:postgresql://{host}:5432/{database}` format.
- `spring.datasource.username` Is the database username.
- `spring.datasource.password` IS the database password.

### Application Architecture

The application architecture is on Domain Driven Design.