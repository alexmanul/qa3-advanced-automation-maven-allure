import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features")
public class TestRunner {
//    mvn clean test -Dcucumber.options="--tags @all"; allure serve target/allure-results/; -Dselenide.headless=true
//            4to zapustith headless
}