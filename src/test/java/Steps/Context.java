package Steps;

import Kafka.Consumer;
import io.cucumber.core.api.Scenario;

import java.util.Collection;
import java.util.Map;

public class Context {

    public Scenario scenario;
    public String pdfFileName;
    public Collection<String> pageContents;
    public String expectedTextAsOneString;
    public String token;

    public Consumer consumer;
    public Map<String, Consumer> consumers;
}
