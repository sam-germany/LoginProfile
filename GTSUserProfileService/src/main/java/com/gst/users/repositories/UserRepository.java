package com.gst.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gst.users.entities.UserEntity;


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, String>{

	
    UserEntity findByEmail(String email);
	
	@Query(value = "select * from users where user_id=:f2", nativeQuery = true)
	UserEntity findByUserId(@Param("f2") Long f1);

      
}
