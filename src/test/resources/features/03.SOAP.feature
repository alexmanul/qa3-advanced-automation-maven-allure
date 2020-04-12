@SOAP
Feature: QA3 - Advanced Automation. SOAP

  Scenario Outline: SOAP01. User can send SOAP request and validate response
	When I send SOAP request to '<URL>' url with body
	"""
		"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
		"   <soapenv:Header/>\n" +
		"   <soapenv:Body>\n" +
		"      <tem:Add>\n" +
		"         <tem:intA>165</tem:intA>\n" +
		"         <tem:intB>2</tem:intB>\n" +
		"      </tem:Add>\n" +
		"   </soapenv:Body>\n" +
		"</soapenv:Envelope>";
	"""
	Examples:
	  | URL                                      |
	  | http://www.dneonline.com/calculator.asmx |