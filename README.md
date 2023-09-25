# Web Quiz Engine

The Web Quiz Engine is a RESTful web application that allows users to create, manage, and solve quizzes. It provides endpoints for quiz creation, retrieval, deletion, updating, and quiz solving. The application also supports user registration and authentication.

## Technologies Used

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* H2 Database (for development)
* Gradle
* JSON

## Key Features

1. `User Registration`: Users can register for an account by providing their email and password. Duplicate email addresses are not allowed.
2. `Quiz Creation`: Authenticated users can create quizzes by specifying the quiz title, question, answer options, and the correct answer(s).
3. `Quiz Retrieval`: Users can retrieve a list of available quizzes. The API supports pagination to view quizzes page by page.
4. `Quiz Solving`: Users can attempt to solve quizzes by providing their selected answer(s). The API checks the answers and provides feedback on correctness.
5. `Quiz Deletion`: Quiz creators can delete their own quizzes.
6. `Quiz Updating`: Quiz creators can update the title, question, answer options, and correct answer(s) of their quizzes.
7. `Quiz Completion History`: Users can view their completed quizzes and check which ones they answered correctly.

## API Endpoints

* `/api/register` (POST): User registration.
* `/api/quizzes` (GET): Retrieve a list of quizzes.
* `/api/quizzes/{id}` (GET): Retrieve a specific quiz by ID.
* `/api/quizzes/{id}` (DELETE): Delete a specific quiz by ID.
* `/api/quizzes/{id}` (PUT): Update a specific quiz by ID.
* `/api/quizzes` (POST): Create a new quiz.
* `/api/quizzes/batch` (POST): Create multiple quizzes at once.
* `/api/quizzes/{id}/solve` (POST): Submit answers to a quiz.
* `/api/quizzes/completed` (GET): Retrieve completed quizzes.

## Getting Started

1. Clone the repository to your local machine:

    ```
   git clone git@github.com:sirio-roberto/web-quiz-java-springboot.git
    ```

2. Build the project using Gradle:

    ```
    ./gradlew build
    ```

3. Run the application:
    ```
    ./gradlew bootRun
   ```

The application will be accessible at http://localhost:8080.


## Usage

1. Register a new user using the `/api/register` endpoint.
2. Create quizzes using the `/api/quizzes` endpoint.
3. Retrieve, update, and delete quizzes using the respective endpoints.
4. Solve quizzes using the `/api/quizzes/{id}/solve` endpoint.
5. View completed quizzes using the `/api/quizzes/completed` endpoint.

## Security

The application uses Spring Security for user authentication. Users must register and log in to create, update, or delete quizzes. Unauthorized users can only view quizzes and solve them.


## Development

For development, the application uses an H2 database. You can access the H2 console at `http://localhost:8080/h2-console` (use JDBC URL: `jdbc:h2:mem:testdb`, username: `sa`, password: `password`).


## Deployment

To deploy the application to a production environment, configure a production database and update the application properties accordingly. Ensure that proper security measures are in place for user data protection.


## Contributions

Contributions to this project are welcome. Feel free to submit bug reports, feature requests, or pull requests.

---

Enjoy using the Web Quiz Engine! If you have any questions or encounter issues, please don't hesitate to reach out to the project maintainers.

`Happy quizzing!`