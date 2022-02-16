package de.thoughtgang.cloud;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static de.thoughtgang.cloud.utils.FileExistsMatcher.fileExistsInDir;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
@TestHTTPEndpoint(WebServiceInletResource.class)
public class WebServiceInletResourceTest {

    @Test
    void thisShouldWork() {

        given()
                .when()
                    .body("hallo Welt")
                    .header("content-ty.pe", "text/plain")
                    .post()
                .then()
                    .statusCode(200)
                    .body(is("hello"))
                    .header("X-MessagePump-FileName", notNullValue())
                    .header("X-MessagePump-FileName", fileExistsInDir("target/output"));
    }



    @Test
    void thisShouldNotWork() {
        given()
                .when()
                .header("content-type", "text/plain")
                .post()
                .then()
                .statusCode(200)
                .body(is("hello"));
    }


}
