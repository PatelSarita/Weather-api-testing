package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",       // this is the most critical line
                "html:target/default-html-report.html"
        },
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        dryRun = false,
        tags = ""
)
public class CukesRunner {

}
