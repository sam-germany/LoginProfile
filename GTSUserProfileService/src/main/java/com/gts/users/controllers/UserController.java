package com.gts.users.controllers;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("users")
public class UserController {

	  @Autowired
	  private UserService uService;
	
	   @PostMapping(path = "/create")
       public UserRest  createUser(@RequestBody  UserDetailsRequestModel userDetails ) throws Exception {
		
		    UserRest  returnValue = new UserRest();
		
		//    UserDto userDto = new UserDto();
	    // 	BeanUtils.copyProperties(userDetails, userDto);
		
		    ModelMapper modelMapper = new ModelMapper();
	     	UserDto userDto = modelMapper.map(userDetails, UserDto.class);
	     	
		    UserDto createdUser = uService.createUser(userDto);
		    returnValue = modelMapper.map(createdUser, UserRest.class);
		
		    return returnValue;
	    }
	
	   @GetMapping(path = "/getUser/{id}")
	   public UserRest getUser(@PathVariable long id) {
		   UserRest returnValue = new UserRest();
		   
		   UserDto userDto = uService.getUserByid(id);
		   BeanUtils.copyProperties(userDto, returnValue);
		   
		   return returnValue;
	   }
	
	   @PutMapping(path = "/update/{id}")
	   public UserRest updateUser(@PathVariable long id , @RequestBody  UserDetailsRequestModel userDetails ) {
		   
		   UserRest returnValue = new UserRest();
		   
		   UserDto userDto = new UserDto();
		   BeanUtils.copyProperties(userDetails, userDto);
		   
		   UserDto updatedUser = uService.updateUser(id, userDto);
		   BeanUtils.copyProperties(updatedUser, returnValue);
		   
		   return returnValue;
		   
	   }
	   
	   @DeleteMapping(path = "/delete/{id}")
	   public OperationsStatusModel deleteUser(@PathVariable  long id) {
		   
		   OperationsStatusModel returnValue = new OperationsStatusModel();
		   returnValue.setOperationName(RequestOperationName.DELETE.name());
		   
		   uService.deleteUser(id);
		   
		   returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		   
		   return returnValue;
	   }
	   
	   @GetMapping(path = "/getAll")
	   public List<UserRest> getAllUsers(@RequestParam(value = "page" , defaultValue = "0") int page,
			                             @RequestParam(value = "limit" , defaultValue = "25") int limit) {
		   
		   List<UserRest> returnValue = new ArrayList<>();
		   
		   List<UserDto> allUsers = uService.getAllUsers(page, limit);
		   
		   for(UserDto userDto : allUsers ) {
			   UserRest userModel = new UserRest();
			   BeanUtils.copyProperties(userDto, userModel);
			   returnValue.add(userModel);
		   }
		   
		   return returnValue;
		   
		   
	   }
}
