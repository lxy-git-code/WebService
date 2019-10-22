package com.org;

import java.io.IOException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

//targetName指定命名空间
@WebService(targetNamespace="http://www.123.321")
public interface MyService {
	@WebResult(name="user")
  User addUser(@WebParam(name="user")User user);
	@WebResult(name="user")
	//authInfo是通过头部来传递的
     List<User> list(@WebParam(header=true,name="authInfo")String authInfo) ;
 	
}
