package com.org;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(endpointInterface="com.org.MyService",targetNamespace="http://www.123.321")
public class MyServiceImpl implements MyService {

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public List<User> list(String authInfo) {
		// TODO Auto-generated method stub
		ArrayList<User> arrayList = new ArrayList<User>();
		User user = new User(0, "zhangsan", "111");
		User user2 = new User(1, "lisi", "222");
		arrayList.add(user);
		arrayList.add(user2);
		return arrayList;
	}



}
