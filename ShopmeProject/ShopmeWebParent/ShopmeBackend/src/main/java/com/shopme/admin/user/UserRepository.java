package com.shopme.admin.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shopme.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	@Query("Select u from User u where u.email = :email")
	public User getUserByEmail(@Param("email") String email);
}
