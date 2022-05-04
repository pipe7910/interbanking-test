package com.globant.interbank.controllers;

import com.globant.interbank.entities.Client;
import com.globant.interbank.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    ClientsService clientsService;

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody Client client) {
        try {
            Client clientSaved = clientsService.save(client);
            HashMap<String, Object> response = new HashMap<>();
            response.put("response", clientSaved);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Object> delete(@RequestBody Client client) {
        try {
            clientsService.delete(client);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody Client client) {
        try {
            Client clientSaved = clientsService.save(client);
            HashMap<String, Object> response = new HashMap<>();
            response.put("response", clientSaved);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        try {
            Client clientFoud = clientsService.findById(id);
            HashMap<String, Object> response = new HashMap<>();
            response.put("response", clientFoud);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
