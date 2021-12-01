package core.bdd.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@demo",
plugin = {"html:target/result.html"},
features = "src/test/resources"
)
public class RunCucumberTest
{

}