import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/feature"
    , glue = {"de.robert.demo"}
    , tags = "@angular"
)

public class TestRunner {

}