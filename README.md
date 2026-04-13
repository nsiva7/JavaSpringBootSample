# Student Management API

A RESTful API built with Spring Boot for managing student records with OpenAPI/Swagger documentation.

## Features

- Complete CRUD operations for Student entity
- In-memory data storage using ConcurrentHashMap
- OpenAPI 3.0 specification
- Interactive Swagger UI documentation
- Automated API documentation deployment to GitHub Pages

## Tech Stack

- Java 21
- Spring Boot 4.0.3
- SpringDoc OpenAPI 2.3.0
- Gradle

## Getting Started

### Prerequisites

- Java 21
- Gradle (or use included wrapper)

### Running the Application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

### API Documentation

Once the application is running, you can access:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## API Endpoints

### Base URL: `/api/students`

| Method | Endpoint             | Description                |
|--------|----------------------|----------------------------|
| GET    | `/api/students`      | Get all students           |
| GET    | `/api/students/{id}` | Get student by ID          |
| POST   | `/api/students`      | Create a new student       |
| PUT    | `/api/students/{id}` | Update an existing student |
| DELETE | `/api/students/{id}` | Delete a student           |

### Student Schema

```json
{
  "id": 1,
  "name": "John Doe",
  "age": 20
}
```

## Testing the API

### Using test.http file

The project includes a `test.http` file with sample requests. You can use it with:

- IntelliJ IDEA (built-in support)
- VS Code with REST Client extension

### Using curl

```bash
# Get all students
curl http://localhost:8080/api/students

# Get student by ID
curl http://localhost:8080/api/students/1

# Create a new student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Johnson","age":21}'

# Update a student
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe Updated","age":25}'

# Delete a student
curl -X DELETE http://localhost:8080/api/students/1
```

## GitHub Pages Setup

The project includes a GitHub Actions workflow that automatically:

1. Builds the Spring Boot application
2. Exports the OpenAPI specification
3. Generates static Swagger UI documentation
4. Deploys to GitHub Pages

### Enable GitHub Pages

1. Go to your repository on GitHub
2. Navigate to **Settings** > **Pages**
3. Under **Build and deployment**:
    - Source: Select **GitHub Actions**
4. Push your code to the `main` branch
5. The workflow will automatically run and deploy the documentation

### View Published Documentation

After the workflow completes, your API documentation will be available at:

```
https://nsiva7.github.io/JavaSpringBootSample
```

### Workflow Permissions

The workflow requires the following permissions (already configured in the YAML file):

- `contents: read`
- `pages: write`
- `id-token: write`

If deployment fails, verify that GitHub Actions has these permissions:

1. Go to **Settings** > **Actions** > **General**
2. Under **Workflow permissions**, ensure:
    - "Read and write permissions" is selected
    - "Allow GitHub Actions to create and approve pull requests" is checked (optional)

## Building the Application

```bash
# Build without tests
./gradlew build -x test

# Build with tests
./gradlew build

# Run tests only
./gradlew test
```

## Project Structure

```
src/
├── main/
│   ├── java/siva.nimmala.springbootsample/
│   │   ├── JavaSpringBootApp.java  # Main application
│   │   ├── Student.java                             # Student record
│   │   ├── StudentController.java                   # REST controller
│   │   └── OpenApiConfig.java                       # OpenAPI configuration
│   └── resources/
│       └── application.properties                   # Application config
└── test/
    └── java/siva.nimmala.springbootsample/
        └── JavaSpringBootAppTests.java
```

## Sample Data

The application comes pre-loaded with sample students:

- ID: 1, Name: "John Doe", Age: 20
- ID: 2, Name: "Jane Smith", Age: 22

## License

Apache 2.0
