#!/bin/bash

# Khai báo đường dẫn tới các module
USER_COMMAND_PATH="user-service/user-command"
USER_QUERY_PATH="user-service/user-query"
NUTRITION_COMMAND_PATH="nutrition-service/nutrition-command"
NUTRITION_QUERY_PATH="nutrition-service/nutrition-query"
# Khai báo thêm nếu có nhiều module hơn

echo "Starting module user-command..."
# shellcheck disable=SC2164
cd "$USER_COMMAND_PATH"
mvn spring-boot:run -Dspring-boot.run.profiles=local &

echo "Starting module user-query..."
# shellcheck disable=SC2164
cd "$USER_QUERY_PATH"
mvn spring-boot:run -Dspring-boot.run.profiles=local &

echo "Starting module nutrition-command..."
# shellcheck disable=SC2164
cd "$NUTRITION_COMMAND_PATH"
mvn spring-boot:run -Dspring-boot.run.profiles=local &

echo "Starting module nutrition-query..."
# shellcheck disable=SC2164
cd "$NUTRITION_QUERY_PATH"
mvn spring-boot:run -Dspring-boot.run.profiles=local &
