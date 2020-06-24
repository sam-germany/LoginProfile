package com.gts.users.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gts.users.model.request.SwaggerLoginRequestModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@ApiOperation("User Login")
@RestController
public class SwaggerController {
	

	// hier we are defining as a explanation how the Response is coming but it is not completed
	 @ApiResponses(value = { 
			         @ApiResponse( code = 200,
			                    message = "Response Headers",
			              responseHeaders =  {
			                         	      @ResponseHeader(name = "authorization",
			                         		          description  = "Bearer <JWT value here>",
			                                             response  = String.class),
			                                		 
			                               	   @ResponseHeader(name ="userId",
			                                		    description = "<Public User Id value here>",
			                                		      response  = String.class)
			                                  }) 
 	              })
	
	
    @ApiOperation("User login")
    @PostMapping("/users/login")
	public void theDummyLogin(@RequestBody SwaggerLoginRequestModel swaggerLoginRequestModel )
	{
		throw new IllegalStateException("This method should not be called it is only for documentation purpose");
	}
}







// NOTE: this method will never be triggered.	
// this controller and method we created only for displaying endpoint in the Swagger documentation.
// it will not be called at run time.at runtime the main SpringSecurity defined uri will be called.
    
// as i put a exception in it, because it is only for Swagger dummy , not for the enduser login purpose.
// in Swagger till now not find any solution to show this  LOGIN ENDPOINT

