package ru.itstep.graduatework_v3.controllers;

import java.util.List;
import ru.itstep.graduatework_v3.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itstep.graduatework_v3.service.UsersService;

@Controller
public class UsersController {

	@Autowired
	UsersService usersService;

	@RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("addUser", "emp", new Users());
	}

	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public ModelAndView processRequest(@ModelAttribute("emp") Users emp) {
		System.out.println("сюда зашли - контроллер");
		usersService.insertUser(emp);
		List<Users> users = usersService.getAllUsers();
		ModelAndView model = new ModelAndView("getUser");
		model.addObject("users", users);
		return model;
	}

 //       @Override
/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String name = request.getParameter("name");
	String pass = request.getParameter("password");
	Users user = new Users();
	System.out.println("сюда зашли - контроллер new");
	user.setName(name);
	user.setPassword(pass);
	user.setRoule((byte) 1);
	usersService.insertUser(user);
*/
//usersDAO.save(user);

	@RequestMapping("/getUsers")
	public ModelAndView getUsers() {
		System.out.println("сюда зашли - контроллер2");
		List<Users> users = usersService.getAllUsers();
		ModelAndView model = new ModelAndView("getUsers");
		model.addObject("users", users);
		return model;
	}

}

