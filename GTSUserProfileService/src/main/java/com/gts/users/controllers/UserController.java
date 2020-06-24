package com.gts.users.controllers;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gts.users.model.request.UserDetailsRequestModel;
import com.gts.users.model.response.OperationsStatusModel;
import com.gts.users.model.response.RequestOperationName;
import com.gts.users.model.response.RequestOperationStatus;
import com.gts.users.model.response.UserRest;
import com.gts.users.services.UserService;
import com.gts.users.shared.dto.UserDto;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("users")
public class UserController {

	  @Autowired
	  private UserService uService;
	
	   @PostMapping(path = "/create"  , produces = { MediaType.APPLICATION_JSON_VALUE })
       public UserRest  createUser(@RequestBody  UserDetailsRequestModel userDetails ) throws Exception {
		
		    UserRest  returnValue = new UserRest();
		
		    ModelMapper modelMapper = new ModelMapper();
	     	UserDto userDto = modelMapper.map(userDetails, UserDto.class);
	     	
		    UserDto createdUser = uService.createUser(userDto);
		    returnValue = modelMapper.map(createdUser, UserRest.class);
		
		    return returnValue;
	    }
	   
	   @ApiImplicitParams({
		   @ApiImplicitParam(name="authorization" , value="${userController.swagger-ui.description}" , paramType = "header")
	   })
	   @GetMapping(path = "/getUser/{id}" ,produces = { MediaType.APPLICATION_JSON_VALUE })
	   public UserRest getUser(@PathVariable long id) {
		   
		   UserRest returnValue = new UserRest();
		   ModelMapper modelMapper = new ModelMapper();
		   
		   UserDto userDto = uService.getUserByid(id);
           returnValue = modelMapper.map(userDto, UserRest.class);
		   
		   
		   return returnValue;
	   }
	   
	   
	   @ApiImplicitParams({
		   @ApiImplicitParam(name="authorization" , value="${userController.swagger-ui.description}" , paramType = "header")
	   })
	   @PutMapping(path = "/update/{id}"  , produces = { MediaType.APPLICATION_JSON_VALUE })
	   public UserRest updateUser(@PathVariable long id , @RequestBody  UserDetailsRequestModel userDetails ) {
		   
		   UserRest returnValue = new UserRest();
		   
		   ModelMapper modelMapper = new ModelMapper();
	     	UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		   
		   UserDto updatedUser = uService.updateUser(id, userDto);
		   returnValue = modelMapper.map(updatedUser, UserRest.class);
		   
		   return returnValue;
		   
	   }
	   
	   
	   @ApiImplicitParams({
		   @ApiImplicitParam(name="authorization" , value="${userController.swagger-ui.description}" , paramType = "header")
	   })
	   @DeleteMapping(path = "/delete/{id}"  , produces = { MediaType.APPLICATION_JSON_VALUE })
	   public OperationsStatusModel deleteUser(@PathVariable  long id) {
		   
		   OperationsStatusModel returnValue = new OperationsStatusModel();
		   returnValue.setOperationName(RequestOperationName.DELETE.name());
		   
		   uService.deleteUser(id);
		   
		   returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		   
		   return returnValue;
	   }
	   
	   
	   @ApiImplicitParams({
		   @ApiImplicitParam(name="authorization" , value="${userController.swagger-ui.description}" , paramType = "header")
	   })
	   @GetMapping(path = "/getAll"  ,produces = { MediaType.APPLICATION_JSON_VALUE })
	   public List<UserRest> getAllUsers(@RequestParam(value = "page" , defaultValue = "0") int page,
			                             @RequestParam(value = "limit" , defaultValue = "25") int limit) {
		   
		   List<UserRest> returnValue = new ArrayList<>();
		   ModelMapper modelMapper = new ModelMapper();
		   
		   List<UserDto> allUsers = uService.getAllUsers(page, limit);
		   
		   for(UserDto userDto : allUsers ) {
			   returnValue.add(modelMapper.map(userDto, UserRest.class));
		   }
		   
		   return returnValue;
		   
		   
	   }
}
