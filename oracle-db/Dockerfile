FROM gvenzl/oracle-xe:21-slim

ENV ORACLE_PASSWORD=Oracle123 \
    APP_USER=AGENDA \
    APP_USER_PASSWORD=AG3nd5

COPY init.sql /container-entrypoint-initdb.d/

EXPOSE 1521