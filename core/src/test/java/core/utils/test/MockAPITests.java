package core.utils.test;

import com.github.tomakehurst.wiremock.client.WireMock;
import core.mockserver.Mock;
import org.junit.*;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class MockAPITests {
    String logindata ="{\"token\":\"ey.token\"}";
    String logoutdata ="{\"status\":true}";
    String userData ="{\"name\":\"auto\"}";
    Mock mock;


    @Before
    public void setup()
    {
        System.out.println("Setup...");
        mock = new Mock(8081);
        mock.setup()
                .then().configureStubWithJsonBodyFile("/pricelist", "pricelist.json")
                .then().configureStub("/login",logindata)
                .then().configureStub("/logout",logoutdata)
                .then().configureStub(WireMock.get(WireMock.urlEqualTo("/user"))
                        .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBody(userData))
                );

    }

    @After
    public void tearDown()
    {
        mock.stop();
    }

    @Test
    public void BasicMockInvokeTest()
    {
        System.out.println("Mock api test");
        get("http://localhost:8081/login").then().body("token",equalTo("ey.token"));
        get("http://localhost:8081/logout").then().body("status",equalTo(true));
        get("http://localhost:8081/user").then().body("name",equalTo("auto"));
        get("http://localhost:8081/pricelist").then().body("camera",equalTo(25));
    }

    @Test
    public void Test2(){
        System.out.println("MOCK..");
    }


}
