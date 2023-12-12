# Database Access with JDBC and DbFacade

This Java project demonstrates basic database access using JDBC, with a `DbFacade` class encapsulating CRUD operations on an existing table, the creation of a new table, and the creation of a new database. The project includes unit tests, including mock tests and an integration test.


## Unit Tests

The unit tests cover various aspects of the `DbFacade` class:

- `testCreateDatabase`: Tests the creation of a new database.
- `testCreateTable`: Tests the creation of a new table in a database.
- `testInsertData`, `testUpdateData`, `testDeleteData`: Tests CRUD operations on an existing table.
- `testIntegration`: An integration test that checks if the methods work together.

## Mocking

Mockito is used for mocking objects in unit tests. The `Mock` and `InjectMocks` annotations are used to create and inject mocks into the tested object.

## Integration Test

The `testIntegration` method is a simple integration test that checks if the methods of `DbFacade` work together. In a real-world scenario, consider using an in-memory database or another approach for integration testing.