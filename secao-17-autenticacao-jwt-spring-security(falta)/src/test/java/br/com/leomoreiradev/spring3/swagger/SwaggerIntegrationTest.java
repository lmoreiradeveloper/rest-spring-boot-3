package br.com.leomoreiradev.spring3.swagger;

import br.com.leomoreiradev.spring3.config.TestConfigs;
import br.com.leomoreiradev.spring3.integrationsteste.testcontainers.AbstracIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstracIntegrationTest {
    @Test
    void shoulDisplauSwaggerUiPage() {

        var content = given()
                        .basePath("swagger-ui/index.html")
                        .port(TestConfigs.SERVER_PORT)
                        .when()
                            .get()
                        .then()
                                .statusCode(200)
                        .extract()
                            .body().asString();

        Assertions.assertTrue(content.contains("Swagger UI"));

    }
}