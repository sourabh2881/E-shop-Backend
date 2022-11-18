package com.sourabh.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.Entity.User;
import com.sourabh.Repository.AddressRepo;
import com.sourabh.Repository.UserRepo;
import com.sourabh.Request.LoginReq;
import com.sourabh.Request.LogoutReq;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	AddressRepo addressRepo;
	
	public int login(LoginReq req) {
		String email = req.getEmail();
		if(userRepo.existsByEmail(email)){
			User user = userRepo.findByEmail(email).get(0);
			if(user.getPassword().equals(req.getPassword())) {
				return user.getId();
			}
		}
		return -1;
	}
	
	public Integer signup(User usr) throws Exception {
		if (userRepo.existsByEmail(usr.getEmail()) || usr.getId()!=0) {
			throw new Exception();
		} 
		userRepo.save(usr);
		return usr.getId();
	}
	
	public boolean logout(LogoutReq req) {
		if(userRepo.existsById(req.getId())){
			return true;
		}
		return false;
	}

	public User getProfile(int id) {
		if(!userRepo.existsById(id)){
			return null;
		}
		User user = userRepo.findById(id);
		return user;
	}

	public boolean updateProfile(User user) {
		User usr  = userRepo.findById(user.getId());
		if(usr==null ) {
			return false;
		}
		usr.setName(user.getName());
		usr.setEmail(user.getEmail());
		usr.setPassword(user.getPassword());
		usr.setPhone(user.getPhone());
		usr.setAddress(user.getAddress());
		userRepo.save(usr);
		return true;
	}
	
}
