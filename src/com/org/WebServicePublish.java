package com.org;

import javax.xml.ws.Endpoint;

public class WebServicePublish {

public static void main(String[] args) {
	String address="http://localhost:8888/ws";
	Endpoint.publish(address, new MyServiceImpl());
}

}
