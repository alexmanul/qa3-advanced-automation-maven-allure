package Utils;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class SOAPHelper {

    private SOAPMessage response;

    public void makeSoapRequest(String soapMessage, String urlSoap) {
        SOAPConnectionFactory factory = null;
        SOAPConnection con = null;
        try {
            factory = SOAPConnectionFactory.newInstance();
            con = factory.createConnection();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        URL url = null;
        try {
            url = new URL(urlSoap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream is = new ByteArrayInputStream(soapMessage.getBytes());
        SOAPMessage request = null;
        try {
            request = MessageFactory.newInstance().createMessage(null, is);
            is.close();
            response = con.call(request, url);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            response.writeTo(baos);

            String resp = baos.toString();
            baos.close();

            if (resp.contains("<errorList code=")) {

            }
            System.out.println("Soap Response: " + resp);

        } catch (IOException | SOAPException e) {
            e.printStackTrace();
        }
    }

}
