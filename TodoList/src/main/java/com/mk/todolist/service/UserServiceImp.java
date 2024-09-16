package com.mk.todolist.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.todolist.dao.UsersDao;
import com.mk.todolist.model.User;
import com.mk.todolist.repository.UserRepository;



@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userrepository;
	
	@Override
	public UsersDao createUserDetails(UsersDao userdao) {
		User save = userrepository.save(userDtoToEntity(userdao)); //UserEntity user = userDtoToEntity(userdto); //converted to userdto to users
		return userEntityToDto(save);
	}
	
	private User userDtoToEntity(UsersDao userdao) 
	{
		User users = new User();
		users.setName(userdao.getName());
		users.setEmail(userdao.getEmail());
		
		
		return users;
	}
	
	private UsersDao userEntityToDto(User user)
	{
		UsersDao users = new UsersDao();
		users.setId(user.getId());
		users.setName(user.getName());
		users.setEmail(user.getEmail());
	
		return users;
	}
}
