package ru.itstep.graduatework_v3.service.impl;

import java.security.Principal;
import java.security.ProtectionDomain;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import ru.itstep.graduatework_v3.dao.impl.UsersDaoImpl;
import ru.itstep.graduatework_v3.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itstep.graduatework_v3.dao.UsersDao;
import ru.itstep.graduatework_v3.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDaoImpl usersDao;

	@Override
	public void insertUser(Users users) {
		usersDao.insertUser(users);
	}

	@Override
	public void updateUser(Users users) {
		usersDao.updateUser(users);
	}

	@Override
	public void insertUsers(List<Users> users) {
		usersDao.insertUsers(users);
	}
	@Override
	public List<Users> getAllUsers() {
		return usersDao.getAllUsers();
	}



	@Override
	public Users getUsersById(Integer Id) {
		Users users = usersDao.getUsersById(Id);
		System.out.println(users);
		return users;
	}

	@Override
	public Users getUsersByName(String userName) {
		System.out.println("userName   "+userName);
		Users users = usersDao.getUsersByName(userName);
		return users;
	}

	@Override
	public Integer getUserRole(Integer Id) {
		Integer intusers = usersDao.getUserRole(Id);
		return intusers;
	}

	@Override
	public Integer getUserIdByName(String userName) {
		Integer intusers = usersDao.getUserId(userName);
		System.out.println(intusers);

		return intusers;
	}

	@Override
	public String getCurrentUserName() {

		UserDetails userDetailsImpl = (UserDetails)
				org.springframework.security.core.context.SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();
		String userName = userDetailsImpl.getUsername();
			return userName;
	}

}