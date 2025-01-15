# JAVA-TEST
JAVA technical test.

The open-api definition is on docs/openapi.yaml

For startup the project, you need to run the project, and it will automaticaly load examples and a in-memory database.:

For testing the project, you can run the following CURL on postman:

```text
curl --location 'http://localhost:8080/products?category=Electronics&sort=Price&page=1&size=20' \
--header 'accept: application/json'
