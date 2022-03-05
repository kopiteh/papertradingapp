package com.zenapp.papertrading.services;

import static com.zenapp.papertrading.Constants.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import com.zenapp.papertrading.PapertradingApplication;
import com.zenapp.papertrading.models.Asset;
import com.zenapp.papertrading.repository.CoinAPIRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class CoinAPIService {

    @Autowired
    private CoinAPIRepo repo;

    public static final String API_KEY = "B8578BD3-004A-4135-B9E8-BDA40CA2A7DF";

    private final Logger logger = Logger.getLogger(PapertradingApplication.class.getName());

    public final String URL_COINAPI = "https://rest.coinapi.io/v1/assets";

    public List<Asset> getAssetListings() throws IOException {

        final String url = UriComponentsBuilder
                .fromUriString(URL_COINAPI)
                .queryParam("filter_asset_id", ASSET_LIST)
                .toUriString();

        final RequestEntity<Void> req = RequestEntity.get(url).header("X-CoinAPI-Key", APIKEY_COINAPI).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        final String body = resp.getBody();

        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            JsonArray test = reader.readArray();

            List<Asset> result = new LinkedList<>();
            result = test.stream()
                    .map(v -> (JsonObject) v)
                    .map(Asset::create)
                    .collect(Collectors.toList());

            repo.batchUpdateListings(result);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }

        return Collections.EMPTY_LIST;
    }
}
