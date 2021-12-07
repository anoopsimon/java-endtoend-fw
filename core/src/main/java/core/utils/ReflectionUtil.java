package core.utils;

import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import core.mockserver.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Locale;

public class ReflectionUtil
{
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * This method is to invoke all get methods with no arguments.
     * @param o
     */
    public static void invokeAllGetterMethodsWithNoParam(Object o)  {
        String lookupText="GET";
        logger.info("Invoking all methods in Class : " + o.getClass().getName());
        for (Method m : o.getClass().getMethods()) {
            if (m.getName().toUpperCase(Locale.ROOT).startsWith(lookupText) && m.getParameterTypes().length == 0) {
                try {
                    final Object r = m.invoke(o);
                    logger.info("Method : "+m.getName() +":" + r);
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
    }

}
