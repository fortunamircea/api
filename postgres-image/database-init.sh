set -o errexit

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    -- api

    CREATE DATABASE api;
    CREATE DATABASE api_test;

    CREATE USER api WITH ENCRYPTED PASSWORD '12345678';

    GRANT ALL PRIVILEGES ON DATABASE api TO api;
    GRANT ALL PRIVILEGES ON DATABASE api TO api;

EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "api" <<-EOSQL
    CREATE EXTENSION pgcrypto;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "api_test" <<-EOSQL
    CREATE EXTENSION pgcrypto;
EOSQL
