package com.sourabh.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.Entity.User;
import com.sourabh.Request.LoginReq;
import com.sourabh.Request.LogoutReq;
import com.sourabh.Response.GeneralResponse;
import com.sourabh.Response.UserResponse;
import com.sourabh.Service.UserService;


@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService ;

	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody LoginReq req) {
		UserResponse userResponse= userService.login(req);
		if(userResponse==null) {
			UserResponse resp = new UserResponse(-1, null);
			return new ResponseEntity<UserResponse>(resp,HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User usr) {
		if(usr.getName()== null || usr.getEmail()== null || usr.getPassword()== null) {
			GeneralResponse badResp = new GeneralResponse("Name, Email and Password shouldn't be empty");
			return new ResponseEntity<GeneralResponse>(badResp,HttpStatus.BAD_REQUEST);
		}
		UserResponse res = new UserResponse();
		try {
			res.setId(userService.signup(usr));
		} catch (Exception e) {
			return new ResponseEntity<UserResponse>(res,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserResponse>(res,HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody LogoutReq req) {
		GeneralResponse resp = new GeneralResponse();
		if(!userService.logout(req)) {
			resp.setResp("Faliure!");
			return new ResponseEntity<GeneralResponse>(resp,HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@GetMapping("/getprofile/{id}")
	public ResponseEntity<?> getprofile(@PathVariable("id") int id) {
		User user = userService.getProfile(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/updateProfile")
	public ResponseEntity<?> updateProfile(@RequestBody User user) {
		GeneralResponse resp = new GeneralResponse();
		if(!userService.updateProfile(user)) {
			resp.setResp("faliure");
			return new ResponseEntity<GeneralResponse>(resp,HttpStatus.UNAUTHORIZED);
		}
		resp.setResp("Success");
		return new ResponseEntity<GeneralResponse>(resp,HttpStatus.OK);
	}
		
}
