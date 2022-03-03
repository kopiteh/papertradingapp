package com.zenapp.papertrading.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Asset {
    
    private String asset_id;
    private String price_usd;

	public String getPrice_usd() {
		return this.price_usd;
	}

	public void setPrice_usd(String price_usd) {
		this.price_usd = price_usd;
	}


	public String getAsset_id() {
		return this.asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

    public static Asset create(JsonObject o) {
        final Asset w = new Asset();
        w.setAsset_id(o.getString("asset_id"));
        //w.setPrice_usd((o.getJsonNumber("price_usd").bigDecimalValue().toPlainString()));
        w.setPrice_usd((o.getJsonNumber("price_usd").bigDecimalValue()).setScale(2, RoundingMode.HALF_EVEN).toString());
        return w;
    }

	public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("asset_id", asset_id)
            .add("price_usd", price_usd)
            .build();
    }
}
