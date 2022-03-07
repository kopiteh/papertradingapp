package com.zenapp.papertrading.controllers;

import com.zenapp.papertrading.PapertradingApplication;
import com.zenapp.papertrading.models.Asset;
import com.zenapp.papertrading.models.TxnOrder;
import com.zenapp.papertrading.repository.CoinAPIRepo;
import com.zenapp.papertrading.services.CoinAPIService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/secure/api")
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

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        repo.getMyPortfolio(username).stream()
                .forEach(v -> arrBuilder.add(v.toJson()));

        return ResponseEntity.ok().body(arrBuilder.build().toString());
    }

    @PostMapping(value="buy")
    public ResponseEntity<String> buyOrder(@RequestBody String body, @RequestHeader(value="subject") String subject) throws UnsupportedEncodingException {

        logger.log(Level.INFO,"<<<<STARTING BUY ORDER>>>>>");
        String username = subject.replace("\"","");

        float balance = repo.checkBalance(username);

        JsonReader reader = Json.createReader(new ByteArrayInputStream(body.getBytes("UTF-8")));
        JsonObject obj = reader.readObject();

        TxnOrder to = TxnOrder.convert(obj);

        logger.log(Level.INFO, "Asset >>> %s".formatted(to.getAsset_id()));
        logger.log(Level.INFO, "Price >>> %s".formatted(to.getPrice_usd()));
        logger.log(Level.INFO, "Quantity >>> %s".formatted(to.getQuantity()));

        float pending_txn = Float.parseFloat(to.getQuantity()) * Float.parseFloat(to.getPrice_usd());

        if (balance > pending_txn){
            repo.addBuyOrder(to, username);
            repo.updateUserBalance(to, username);

            return ResponseEntity.ok(obj.toString());
        }

        return null;
    }


    @PostMapping(value="sell")
    public ResponseEntity<String> sellOrder(@RequestBody String body, @RequestHeader(value="subject") String subject) throws UnsupportedEncodingException {

        logger.log(Level.INFO,"<<<<STARTING SELL ORDER>>>>>");
        String username = subject.replace("\"","");

        JsonReader reader = Json.createReader(new ByteArrayInputStream(body.getBytes("UTF-8")));
        JsonObject obj = reader.readObject();

        TxnOrder to = TxnOrder.convert(obj);

        logger.log(Level.INFO, "Asset >>> %s".formatted(to.getAsset_id()));
        logger.log(Level.INFO, "Price >>> %s".formatted(to.getPrice_usd()));
        logger.log(Level.INFO, "Quantity >>> %s".formatted(to.getQuantity()));

        repo.addBuyOrder(to, username);
        repo.updateUserBalance(to, username);

        return ResponseEntity.ok(obj.toString());
    }
}

