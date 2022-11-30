package com.sourabh.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sourabh.Entity.User;
import com.sourabh.Repository.UserRepo;
import com.sourabh.Request.LoginReq;
import com.sourabh.Request.LogoutReq;
import com.sourabh.Response.UserResponse;
import com.sourabh.Security.JwtUtility;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtility jwtUtility;
	
	@Autowired
	private UserCredentialService userCredentialService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserResponse login(LoginReq request) {
		String email = request.getEmail();
		if(userRepo.existsByEmail(email)){
			User user = userRepo.findByEmail(email);
			if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
				UserDetails userDetails = userCredentialService.loadUserByUsername(request.getEmail());
			    String token = jwtUtility.generateToken(userDetails);
				UserResponse userResponse = new UserResponse(user.getId(),token);
			    return userResponse;
			}
		}
	 
		return null;
	}
	
	public Integer signup(User usr) throws Exception {
		if (userRepo.existsByEmail(usr.getEmail()) || usr.getId()!=0) {
			throw new Exception();
		} 
		usr.setPassword(passwordEncoder.encode(usr.getPassword()));
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
