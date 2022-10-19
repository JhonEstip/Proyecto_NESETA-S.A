package com.company.netesa.controller;

import java.util.List;

import org.json.JSONObject;
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
    	JSONObject error = new JSONObject();
		error.put("access", false);	
		error.put("msg", "Error en credenciales, verifica e intenta nuevamente");
    	JSONObject errorEmpty = new JSONObject();
    	errorEmpty.put("access", false);	
    	errorEmpty.put("msg", "Los campos estan vacios, intente nuevamente");
    	if(userDetalle.getEmail()!="") {
    		List<User> user = userService.findUserByEmail(userDetalle.getEmail());
			if(user!=null && user.size()>0) {				
				if(user.get(0).getPassword().equals(userDetalle.getPassword())) {
					JSONObject main = new JSONObject();
					String tokenJwt = jwtUtil.getJWTToken(user.get(0).getNumeroDocumento()+"");	
					main.put("access", tokenJwt.replace("Bearer ", "")).toString();	
					main.put("user", user);
		            return ResponseEntity.ok(main.toString());
				}
				return ResponseEntity.ok(error.toString());
			} 
			return ResponseEntity.ok(error.toString());
    	}    	
		return ResponseEntity.ok(errorEmpty.toString());
	}
}