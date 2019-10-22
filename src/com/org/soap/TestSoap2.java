package com.org.soap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.org.User;

public class TestSoap2 {
	public static void main(String[] args) throws XPathExpressionException {

		try {
			URL url = new URL("http://localhost:8888/ws?wsdl");
			QName qName = new QName("http://www.123.321", "MyServiceImplService");
			Service service = Service.create(url, qName);

			// 创建dispatcher基于Service.Mode.MESSAGE
			Dispatch<SOAPMessage> createDispatch = service.createDispatch(
					new QName("http://www.123.321", "MyServiceImplPort"), SOAPMessage.class, Service.Mode.MESSAGE);

			// 创建Soapmessage
			MessageFactory newInstance = MessageFactory.newInstance();
			SOAPMessage createMessage = newInstance.createMessage();
			SOAPPart soapPart = createMessage.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			SOAPHeader header = envelope.getHeader();
			if (header == null) {
				header = envelope.addHeader();
			}

			header.addHeaderElement(new QName("http://www.123.321", "authInfo", "q0")).setValue("aabbccdd");
			;

			SOAPBody body = envelope.getBody();

			SOAPBodyElement addBodyElement = body.addBodyElement(new QName("http://www.123.321", "list", "q0"));
			/*
			 * addBodyElement.addChildElement("a").setValue("22");
			 * addBodyElement.addChildElement("b").setValue("55");
			 */

			// 發送消息
			SOAPMessage invoke = createDispatch.invoke(createMessage);
			createMessage.writeTo(System.out);

			Document extractContentAsDocument = invoke.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			XPathFactory newInstance2 = XPathFactory.newInstance();

			NodeList list = (NodeList) newInstance2.newXPath().evaluate("//user", extractContentAsDocument,
					XPathConstants.NODESET);
			JAXBContext jax = JAXBContext.newInstance(User.class);

			for (int i = 0; i < list.getLength(); i++) {
				Unmarshaller unmarshaller = jax.createUnmarshaller();
				User userbean = (User) unmarshaller.unmarshal(list.item(i));
				System.out.println(userbean.toString());
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
