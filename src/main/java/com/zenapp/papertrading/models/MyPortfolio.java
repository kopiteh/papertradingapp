package com.zenapp.papertrading.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class MyPortfolio {

    private String username;
    private String asset_id;
    private float price_usd;
    private float quantity;
    private float value;

    public float getPrice_usd() {
        return price_usd;
    }
    public void setPrice_usd(float price_usd) {
        this.price_usd = price_usd;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getAsset_id() {
        return asset_id;
    }
    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public float getQuantity() {
        return quantity;
    }
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getValue() {
        return value;
    }
    public void setValue(float value) {
        this.value = value;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("username", username)
                .add("asset_id", asset_id)
                .add("price_usd", price_usd)
                .add("quantity", quantity)
                .add("value", value)
                .build();

    }
}
