# Product Config Task

## Getting Started

Please follow these instructions to have SS up and running on your local machine for development and testing purposes.

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

On the `dev` profile, Product-Config-Task Server expects to find an Postgres instance listening on port `5432`. One way to run
this dependency is to use the docker compose configurations in the [docker-compose](docker-compose.yml)
file. In order to do that just `up`:

```bash
docker-compose up
```

### Additional Configuration Files

If you're going to use additional configuration locations in addition to the default locations, specify that location
using the
`spring.config.additional-location` configuration property.

### Oracle Configurations

In order to have a working connection to Oracle, we should provide:

- `spring.datasource.url` Is the JDBC URL to Postgres instance with `jdbc:postgresql://{host}:5432/{database}` format.
- `spring.datasource.username` Is the database username.
- `spring.datasource.password` IS the database password.