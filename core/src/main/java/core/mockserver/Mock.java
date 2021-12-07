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

    public Mock configureStub(MappingBuilder mappingBuilder)
    {
        ReflectionUtil.invokeAllGetterMethodsWithNoParam(mappingBuilder.build());
        wireMockServer.stubFor(mappingBuilder);

        return this;
    }

    public void invoker(StubMapping o)  {
        //final Object o = "";
        for (Method m : o.getClass().getMethods()) {
            if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                try {
                    final Object r = m.invoke(o);
                    System.out.println(m.getName() +":" + r);

                } catch (Exception e) {
                    e.printStackTrace();
                    // do your thing with r
                }
            }
        }
    }

    public void stop() {
        wireMockServer.shutdownServer();
    }

    public static void main(String[] args) {
        WireMockServer m =new WireMockServer(8095);
        m.start();
    }
}
