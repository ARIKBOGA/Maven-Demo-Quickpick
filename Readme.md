
# Maven Demo Quickpick - API Testing Project

A lightweight and efficient framework for API testing using **Java**, **RestAssured**, and **JUnit 5**, powered by Maven and Allure for reporting.

This project is designed for testing APIs with clean, maintainable test cases and generating visually appealing reports using Allure.

---

## **Features**
- **API Testing**: Built with RestAssured (v5.5.0) for robust API interactions.
- **Unit Testing Framework**: Leverages JUnit 5 (JUnit Jupiter v5.10.2) for structured and flexible testing.
- **Assertions**: Hamcrest Matchers (v2.2) for expressive and readable assertions.
- **Reports**: Allure Test Reports (v2.10.0) for detailed and visually appealing test execution results.
- **Maven Integration**: Simplifies dependency management and test execution.

---

## **Project Setup**

### **Prerequisites**
1. Install **Java** (8 or later) and set `JAVA_HOME`.
2. Install **Maven** (3.6.3 or later).
3. (Optional) Install the **Allure Command-Line Tool** for better local report management. You can download it from [Allure GitHub](https://github.com/allure-framework/allure2).

### **Clone the Repository**
```bash
git clone https://github.com/your-repo/maven-demo-quickpick.git
cd maven-demo-quickpick
```

### **Dependencies Used**
This project uses the following dependencies (managed via Maven):
- RestAssured `5.5.0`
- JUnit Jupiter `5.10.2`
- Allure Test Reports `2.10.0`
- Hamcrest Matchers `2.2`
- Maven Surefire Plugin `3.0.0-M9`

---

## **How to Run Tests**

### **Step 1: Execute Tests**
Run the following Maven command to execute all tests:
```bash
mvn clean test
```

After running, test results are generated in the `target/allure-results` directory.

### **Step 2: Generate Allure Report**
Create an Allure report using:
```bash
mvn allure:report
```
This will generate the report under `target/site/allure-maven-plugin`.

### **Step 3: Serve the Report**
Launch the Allure report in a web browser with:
```bash
mvn allure:serve
```

This command starts a local server to view the report interactively.

---

## **Project Structure**

```
maven-demo-quickpick/
│
├── src/
│   ├── main/
│   │   └── java/         # Application (if required)
│   └── test/
│       └── java/         # Test cases written with JUnit and RestAssured
│
├── target/               # Compiled classes and test results (auto-generated)
├── pom.xml               # Maven configuration file
└── README.md             # Documentation (this file)
```

---

## **Sample Test Example**

A simple example of an API test using RestAssured and JUnit 5:
```java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class SampleTest {

    @Test
    public void getUsersTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = RestAssured
            .given()
            .when()
            .get("/users/1");

        response.then()
                .statusCode(200)
                .body("username", equalTo("Bret"));
    }
}
```

---

## **Common Maven Commands**

| Command                  | Description                            |
|--------------------------|----------------------------------------|
| `mvn clean`              | Cleans the `target` directory         |
| `mvn test`               | Runs all tests                        |
| `mvn clean test`         | Cleans and runs tests                 |
| `mvn allure:report`      | Generates an Allure report            |
| `mvn allure:serve`       | Serves the Allure report locally      |

---

## **Allure Report Preview**
The Allure report provides:
- Test execution summary
- Detailed step-by-step execution logs
- Visual charts and graphs
- Attachment support for debugging

---

## **Contribution**
Contributions are welcome! Feel free to fork this project, submit issues, or create pull requests.

---

## **License**
This project is licensed under [ARIKBOGA](https://github.com/ARIKBOGA).
