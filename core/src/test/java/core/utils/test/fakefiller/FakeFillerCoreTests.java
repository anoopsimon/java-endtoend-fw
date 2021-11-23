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
        Assert.assertTrue("All rows in test datasheet should be captured. There are two records in file " + path,data.size() == 2);
        Assert.assertTrue("Test Case name should be captured as Anwser",
                data.stream().findFirst().get().containsKey("Answer"));

    }
}
