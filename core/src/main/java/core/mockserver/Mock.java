package core.mockserver;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import core.utils.FakeFiller;
import core.utils.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Mock {
    private static final Logger logger = LoggerFactory.getLogger(Mock.class);

    int port;
    WireMockServer wireMockServer;
    public Mock(int port){
        this.port=port;
    }

    public Mock and() {return this;}
    public Mock then() {return this;}

    public Mock setup()
    {
        logger.info("Initializing mock api on port " + this.port);
        wireMockServer = new WireMockServer(this.port);
        wireMockServer.start();
        return this;
    }

    public Mock configureStub(String resource,String body,String... contentType)
    {
        logger.info("Configure mock endpoint, with resource " + resource+
                "and Body " + body);

        String contentTypeVal= contentType.length > 0 ? contentType[0] : "application/json";
        logger.info("Setting Content Type as : " + contentTypeVal);
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(resource))
                        .willReturn(WireMock.aResponse().withHeader("Content-Type", contentTypeVal)
                        .withStatus(200)
                        .withBody(body)));

        return this;
    }

    public Mock configureStubWithJsonBodyFile(String resource,String bodyFile,String... contentType)
    {
        logger.info("Configure mock endpoint, with resource " + resource+
                "and Body " + bodyFile);

        String contentTypeVal= contentType.length > 0 ? contentType[0] : "application/json";
        logger.info("Setting Content Type as : " + contentTypeVal);
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo(resource))
                        .willReturn(WireMock.aResponse().withHeader("Content-Type", contentTypeVal)
                        .withStatus(200)
                        .withBodyFile(bodyFile)));

        return this;
    }

    /**
     * This method provides an option to build a resource based on your configuration.
     * @param mappingBuilder
     * @return
     */
    public Mock configureStub(MappingBuilder mappingBuilder)
    {
        logger.info("Configure a new resource using mapping builder.");
        ReflectionUtil.invokeAllGetterMethodsWithNoParam(mappingBuilder.build());
        wireMockServer.stubFor(mappingBuilder);

        return this;
    }

    /**
     * Kill mock server instance.
     */
    public void stop() {
        logger.info("Killing mock serve running on port "  + this.port);
        wireMockServer.shutdownServer();
    }

}
