package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userDustin = new User("dustin@hoang.com","123","Hoang","Dustin");
		userDustin.addRole(roleAdmin);
		
		User savedUser = repo.save(userDustin);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRole() {
		User userRavi = new User("ravin@hoang.com","123","Ravi","Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = repo.save(userRavi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User userDustin = repo.findById(1).get();
		System.out.println(userDustin);
		assertThat(userDustin).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetail() {
		User userDustin = repo.findById(1).get();
		userDustin.setEnabled(true);
		userDustin.setEmail("dustinhoang@gmail.com");
		
		repo.save(userDustin);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userRavi = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSalesperson);
		
		repo.save(userRavi);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "kaka@kaka.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById() {
		Integer id = 1;
		Long countById = repo.countById(id);
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testEnableUser() {
		Integer id = 3;
		repo.updateEnableStatus(id, true);
	}
}
