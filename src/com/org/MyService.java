package com.org;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MyService {
	@WebResult(name="user")
  User addUser(@WebParam(name="user")User user);
 	
}
