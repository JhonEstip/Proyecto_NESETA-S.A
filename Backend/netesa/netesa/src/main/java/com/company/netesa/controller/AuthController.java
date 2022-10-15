package com.company.netesa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.netesa.model.User;
import com.company.netesa.service.UserService;
import com.company.netesa.util.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;
    
    @PostMapping
	public ResponseEntity<?> createtoken(@RequestBody User userDetalle){
		List<User> user = userService.findUserById(userDetalle.getId());
		if(user!=null && user.size()>0) {
			String tokenJwt = jwtUtil.getJWTToken(user.get(0).getNumeroDocumento()+"");
            return ResponseEntity.ok(tokenJwt);
		} 
		return ResponseEntity.notFound().build();
	}
}