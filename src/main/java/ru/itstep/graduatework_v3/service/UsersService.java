package ru.itstep.graduatework_v3.service;

import java.util.List;

import org.apache.catalina.User;
import ru.itstep.graduatework_v3.model.Users;

public interface UsersService {
	void insertUser(Users emp);
	void updateUser(Users emp);
	void insertUsers(List<Users> users);
	List<Users> getAllUsers();
	Users getUsersById(Integer id);
	Users getUsersByName(String userName);
	Integer getUserRole(Integer Id);
	Integer getUserIdByName(String userName);
	String getCurrentUserName();
}