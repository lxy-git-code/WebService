package com.org.soap;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class CreateSoap {

	public static void main(String[] args) {
		try {
			/*
			 * SOAP–≠“È SOAPEnvelope SOAPHeader SOAPBody SOAPElement
			 * 
			 * <soapenv:Envelope xmlns:q0="http://service.zttc.org/"
			 * 
			 * xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
			 * 
			 * xmlns:xsd="http://www.w3.org/2001/XMLSchema"
			 * 
			 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			 * 
			 * <soapenv:Header> </soapenv:Header>
			 * 
			 * <soapenv:Body>
			 * 
			 * <q0:minus> <arg0>1</arg0> <arg1>1</arg1> </q0:minus> </soapenv:Body>
			 * 
			 * </soapenv:Envelope>
			 * 
			 * 
			 */

			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPPart soapPart = soapMessage.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addTextNode("\n");

			envelope.getHeader().addTextNode("\n");
			SOAPBody body = envelope.getBody();
			body.addTextNode("\n");
			QName qName = new QName("http://org.com/", "add", "q0");
			SOAPBodyElement bodyElement = body.addBodyElement(qName);
			bodyElement.addTextNode("\n");
			bodyElement.addChildElement("id", "q0").setValue("1");
			bodyElement.addTextNode("\n");
			bodyElement.addChildElement("username", "q0").setValue("lxy");
			bodyElement.addTextNode("\n");
			bodyElement.addChildElement("password", "q0").setValue("123123");
			bodyElement.addTextNode("\n");
			soapMessage.writeTo(System.out);

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
