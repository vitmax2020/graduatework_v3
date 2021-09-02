package ru.itstep.graduatework_v3.dao;

import java.util.List;

import ru.itstep.graduatework_v3.model.Users;

public interface UsersDao {
	void insertUser(Users cus);
	void updateUser(Users cus);
	void deleteUser(Integer Id);
	void insertUsers(List<Users> users);
	List<Users> getAllUsers();
	Users getUsersById(Integer Id);
	Users getUsersByName(String userName);
	Integer getUserRole(Integer Id);
	Integer getUserId(String userId);
	String getUserName(Integer userId);
}