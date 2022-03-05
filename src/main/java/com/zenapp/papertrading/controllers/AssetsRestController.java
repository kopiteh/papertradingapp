package com.zenapp.papertrading.controllers;

import com.zenapp.papertrading.PapertradingApplication;
import com.zenapp.papertrading.models.Asset;
import com.zenapp.papertrading.repository.CoinAPIRepo;
import com.zenapp.papertrading.services.CoinAPIService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class AssetsRestController {

    private final Logger logger = Logger.getLogger(PapertradingApplication.class.getName());


    @Autowired
    private CoinAPIRepo repo;

    @Autowired
    private CoinAPIService coinapiService;


    @GetMapping(value = "assets")
    public ResponseEntity<String> getAssets() {

        logger.log(Level.INFO, "Loading assets from resource api/assets");
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

    @GetMapping(value = "leaderboard")
    public ResponseEntity<String> getLeaderboard() {

        //String username = headers.get("subject");

        logger.log(Level.INFO, "Testing if i can read the headers");
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        repo.getLeaderboard().stream()
                .forEach(v -> arrBuilder.add(v.toJson()));

        return ResponseEntity.ok().body(arrBuilder.build().toString());
    }

    @GetMapping(value="myportfolio")
    public ResponseEntity<String> getMyPortfolio(@RequestHeader(value="subject") String subject){

        String username = subject.replace("\"",""); 

        logger.log(Level.INFO, "headers from client >>> %s \n\n\n".formatted(username));
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        repo.getMyPortfolio(username).stream()
                .forEach(v -> arrBuilder.add(v.toJson()));

        return ResponseEntity.ok().body(arrBuilder.build().toString());
    }
}
