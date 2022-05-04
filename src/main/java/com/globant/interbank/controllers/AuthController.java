package com.globant.interbank.controllers;

import com.globant.interbank.utilities.ClientAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    ClientAuthentication clientAuthentication;


    @GetMapping()
    public ResponseEntity<Object> getAuthorization() {
        try {
            String token = clientAuthentication.generateToken();
            HashMap<String, Object> response = new HashMap<>();
            response.put("response", token);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value ="/validate" , params="token")
    public ResponseEntity<Object> getAuthorization(@RequestParam(name = "token") String token) {
        try {
            boolean result = clientAuthentication.isValidToken(token);
            HashMap<String, Object> response = new HashMap<>();
            response.put("response", result);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
