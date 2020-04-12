package Steps;

import Utils.SOAPHelper;
import io.cucumber.java.en.And;

public class SOAPSteps {
    final SOAPHelper soapHelper = new SOAPHelper();

    @And("I send SOAP request to '(.*)' url with body")
    public void ISetFollowingPropertiesAndNavigarteToURL(String message, String url) {
        soapHelper.makeSoapRequest(message, url);
    }
}