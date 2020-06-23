package com.gts.users.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gts.users.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	
    UserDto createUser(UserDto userDto);
	
    UserDto getUserByid(long user_id);
 
    UserDto updateUser (long user_id, UserDto userDto);
    
    void deleteUser(long user_id);
    
    List<UserDto> getAllUsers(int page, int limit);
    
    UserDto getUserByEmail(String email);
    
}
