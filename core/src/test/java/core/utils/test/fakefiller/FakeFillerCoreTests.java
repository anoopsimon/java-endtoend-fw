package core.utils.test.fakefiller;

import core.utils.FakeFiller;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.TreeMap;

public class FakeFillerCoreTests
{
    @Test
    public void verifyFakeFillerGetFlow()
    {
        String path=System.getProperty("user.dir")+ "/src/test/resources/TestData_FF.xlsx";
        String testCase= "TC01";

        List<TreeMap<String, String>> data= FakeFiller.getFlow(path,testCase);
        Assert.assertTrue(data.size() == 2);

    }
}
