package Steps;

import io.cucumber.core.api.Scenario;

import java.util.Collection;

public class Context {

    public Scenario scenario;
    public String pdfFileName;
    public Collection<String> pageContents;
    public String expectedTextAsOneString;
    public String token;
}
