package com.org;

import java.io.IOException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

//targetNameָ�������ռ�
@WebService(targetNamespace="http://www.123.321")
public interface MyService {
	@WebResult(name="user")
  User addUser(@WebParam(name="user")User user);
	@WebResult(name="user")
	//authInfo��ͨ��ͷ�������ݵ�
     List<User> list(@WebParam(header=true,name="authInfo")String authInfo) ;
 	
}
