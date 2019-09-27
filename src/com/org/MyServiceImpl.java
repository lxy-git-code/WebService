package com.org;

import javax.jws.WebService;

@WebService(endpointInterface="com.org.MyService")
public class MyServiceImpl implements MyService {

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return user;
	}



}
