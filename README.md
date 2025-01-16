# JAVA-TEST
JAVA technical test.

The open-api definition is on docs/openapi.yaml

For startup the project, you need to run the project, and it will automaticaly load examples and a in-memory database.:

For testing the project, you can run the following CURL on postman:

```text
curl --location 'http://localhost:8080/api/products?category=Electronics&sort=price&sortDirection=asc&page=1&size=10' \
--header 'accept: application/json'
