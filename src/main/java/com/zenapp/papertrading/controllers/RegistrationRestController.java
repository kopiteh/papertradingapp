package com.zenapp.papertrading.controllers;

import com.zenapp.papertrading.PapertradingApplication;
import com.zenapp.papertrading.repository.UserRepo;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api/register")
public class RegistrationRestController {

    private final Logger logger = Logger.getLogger(PapertradingApplication.class.getName());

    @Autowired
    private UserRepo userRepo;

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody String body){

        try {
            JsonReader reader = Json.createReader(new ByteArrayInputStream(body.getBytes("UTF-8")));
            JsonObject obj = reader.readObject();

            this.userRepo.addUser(obj.getString("username"),obj.getString("email"),obj.getString("password"));



            return ResponseEntity.accepted().body(obj.toString());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something is wrong. Please try registering again.");
        }

    }
}
