package Tests;

import Utils.Helpers.SOAPHelper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SOAPTest {
    final SOAPHelper soapHelper = new SOAPHelper();
    String urlSoap = "http://www.dneonline.com/calculator.asmx";
    String soapMessage =
            """
                    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tem="http://tempuri.org/">
                       <soapenv:Header/>
                       <soapenv:Body>
                          <tem:Add>
                             <tem:intA>165</tem:intA>
                             <tem:intB>2</tem:intB>
                          </tem:Add>
                       </soapenv:Body>
                    </soapenv:Envelope>""";

    @Test
    public void TEST_SOAP_01() {
        soapHelper.makeSoapRequest(soapMessage, urlSoap);
    }
}
