package Tests;

//import lombok.extern.log4j.Log4j;
import Utils.Helpers.SOAPHelper;
import org.junit.Test;

//@Log4j
public class SOAPTest {
    final SOAPHelper soapHelper = new SOAPHelper();

    String urlSoap = "http://www.dneonline.com/calculator.asmx";
    String soapMessage =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <tem:Add>\n" +
                    "         <tem:intA>165</tem:intA>\n" +
                    "         <tem:intB>2</tem:intB>\n" +
                    "      </tem:Add>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";

    @Test
    public void TEST_SOAP_01() {
        soapHelper.makeSoapRequest(soapMessage, urlSoap);
    }
}
