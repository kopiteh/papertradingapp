package com.zenapp.papertrading.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.zenapp.papertrading.PapertradingApplication;
import com.zenapp.papertrading.models.Asset;
import com.zenapp.papertrading.repository.CoinAPIRepo;
import com.zenapp.papertrading.services.CoinAPIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(path="/api")
public class AssetsRestController {

    private final Logger logger = Logger.getLogger(PapertradingApplication.class.getName());


    @Autowired
    private CoinAPIRepo repo; 

    @Autowired
    private CoinAPIService coinapiService; 


    @GetMapping(value="assets")
    public ResponseEntity<String> getAssets(){

        List<Asset> assetList = Collections.emptyList(); 

        try {
            assetList = coinapiService.getAssetListings();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "getLatestPrice", e);
        }

        final JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        
        assetList.stream()
            .forEach(v -> arrBuilder.add((v.toJson())));
        
        return ResponseEntity.ok().body(arrBuilder.build().toString());
    }
    
    @GetMapping(value="leaderboard")
    public ResponseEntity<String> getLeaderboard(){

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder(); 
        repo.getLeaderboard().stream()
            .forEach(v -> arrBuilder.add(v.toJson()));
        
        return ResponseEntity.ok().body(arrBuilder.build().toString());
    }
}
