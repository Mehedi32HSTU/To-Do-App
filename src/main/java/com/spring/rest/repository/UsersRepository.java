package com.spring.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.rest.models.TaskDetails;
import com.spring.rest.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUserName(String userName);
	
	@Query(value = "select * from users where users.is_active = true and users.user_id=?1", nativeQuery = true)
	Users findByUserId(Long userId);
	

}
