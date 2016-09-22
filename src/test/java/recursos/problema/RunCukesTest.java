package recursos.problema;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        glue = {"cucumber.runtime.java.spring.contextconfig",
            "cucumber.runtime.java.spring.commonglue",
            "cucumber.api.spring","recursos.problema"})
public class RunCukesTest {
}