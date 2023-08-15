package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping("/checkUser")
	public boolean checkLoginUser(@RequestParam String userName,String password) {
	
		boolean res = false;
		
		User user = userRepo.findByUserName(userName);
		
		if( (user != null) && (user.getUserName().equalsIgnoreCase(userName))
				&& (user.getPassword().equalsIgnoreCase(password))) {
			res = true;
		}
		
		return res;
	}
	
	@RequestMapping("/user/add")
	public String addNewUser(@RequestParam String userName,String password) {
	
		userRepo.save(new User(userName,password));
		
		return "Added user";
	}


}