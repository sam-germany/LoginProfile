package com.gst.users.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gst.users.entities.UserEntity;
import com.gst.users.exceptions.UserServiceException;
import com.gst.users.model.response.ErrorMessageEnum;
import com.gst.users.repositories.UserRepository;
import com.gst.users.services.UserService;
import com.gst.users.shared.dto.UserDto;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	BCryptPasswordEncoder  bCryptPasswordEncoder;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {

		if(uRepo.findByEmail(userDto.getEmail())  != null) 
			     throw new UserServiceException(ErrorMessageEnum.NO_RECORD_FOUND.getErrorMessageEnum());
		
		  UserEntity userEntity = new UserEntity();
       	  BeanUtils.copyProperties(userDto, userEntity);

       	  
      	    userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

  	     	UserEntity  storedUserDetails = uRepo.save(userEntity);

    		UserDto  returnValue = new UserDto();
       	    BeanUtils.copyProperties(storedUserDetails, returnValue);

	return returnValue;
	}

 
	   @Override
	    public UserDto getUserByid(long user_id) {
             UserDto returnValue = new UserDto();
             
             UserEntity userEntity = uRepo.findByUserId(user_id);
             
             if( userEntity == null) 
			     throw new UserServiceException(ErrorMessageEnum.NO_RECORD_FOUND.getErrorMessageEnum());
             
             BeanUtils.copyProperties(userEntity, returnValue);
               
		   return returnValue;
	   }


	    @Override
	    public UserDto updateUser(long user_id, UserDto userDto) {

	    	UserDto returnValue = new UserDto();
	    	UserEntity userEntity  = uRepo.findByUserId(user_id);
	    	
	    	if(userEntity == null)
	    		         throw new UserServiceException(ErrorMessageEnum.NO_RECORD_FOUND.getErrorMessageEnum());
	    	
	    	userEntity.setName(userDto.getName());
	    	userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
	    	
	    	UserEntity updatedUser = uRepo.save(userEntity);
	    	BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}


		@Override
		public void deleteUser(long user_id) {

			  UserEntity userEntity = uRepo.findByUserId(user_id);
			  
			  if(userEntity == null)
				          throw new UserServiceException(ErrorMessageEnum.NO_RECORD_FOUND.getErrorMessageEnum());
			
			  uRepo.delete(userEntity);
		}


		@Override
		public List<UserDto> getAllUsers(int page, int limit) {

			 List<UserDto> returnValue = new ArrayList<>();
			 if(page > 0) page -=1;      // means   if(page > 0) page =  page -1;    this we put because page starts
                                         // normally from 0,1,2....  but  0_th   reading page starts from zero is not
                                         // so common, so i put the logic that the user put PAGE Nr = 1   and we read 
                                          // internally  the o_th page
			 Pageable pageableRequest   = PageRequest.of(page, limit);
			
			 Page<UserEntity> usersPage = uRepo.findAll(pageableRequest);
			 List<UserEntity> users = usersPage.getContent();
			 
			 
			 for(UserEntity userEntity : users) {
				 UserDto userDto = new UserDto();
				 BeanUtils.copyProperties(userEntity, userDto);
				 returnValue.add(userDto);
			 }
			 
			
			return returnValue;
		}
}
