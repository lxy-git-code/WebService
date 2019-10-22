package com.org.soap;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;

import com.org.User;

public class SendMessage {

	public static void main(String[] args) throws MalformedURLException, JAXBException,
			TransformerFactoryConfigurationError, TransformerException, XPathExpressionException {
		URL url = new URL("http://localhost:8888/ws?wsdl");
		QName qName = new QName("http://org.com/", "MyServiceImplService");
		Service service = Service.create(url, qName);
		Dispatch<Source> dispatch = service.createDispatch(new QName("http://org.com/", "MyServiceImplPort"),
				Source.class, Service.Mode.PAYLOAD);
		JAXBContext jax = JAXBContext.newInstance(User.class);
		Marshaller marshaller = jax.createMarshaller();
		User user = new User(1, "admin", "111");
		//È¡ÏûstartDocument or endDocument <?xml version="1.0" encoding="utf-8"?>
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(user, stringWriter);

		//String msg = "<q0:addUser xmlns:q0=\"http://org.com/\">" + stringWriter.toString() + "</q0:addUser>";
		String msg1="<q0:authInfo>asddasd</q0:authInfo>";
		String msg="<q0:list xmlns:q0=\"http://org.com/\">"+"</q0:list>";
		
		StreamSource source = new StreamSource(new StringReader(msg1+msg));
		Source invoke = dispatch.invoke(source);

		Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
		DOMResult result = new DOMResult();

		newTransformer.transform(invoke, result);

		XPath newXPath = XPathFactory.newInstance().newXPath();
		NodeList list = (NodeList) newXPath.evaluate("//user ", result.getNode(), XPathConstants.NODESET);

		Unmarshaller unmarshaller = jax.createUnmarshaller();
		User userbean = (User) unmarshaller.unmarshal(list.item(1));
		System.out.println(userbean.toString());

	}
}
