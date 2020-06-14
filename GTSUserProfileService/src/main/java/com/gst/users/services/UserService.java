package com.gst.users.services;


import java.util.List;

import com.gst.users.shared.dto.UserDto;

public interface UserService {

	
    UserDto createUser(UserDto userDto);
	
    UserDto getUserByid(long user_id);
 
    UserDto updateUser (long user_id, UserDto userDto);
    
    void deleteUser(long user_id);
    
    List<UserDto> getAllUsers(int page, int limit);
    
}
