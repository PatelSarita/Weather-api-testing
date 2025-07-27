package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-reports/cucumber.html"
        },
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        dryRun = false,
        tags = ""
)
public class CukesRunner {

}
