package com.zenapp.papertrading.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Leaderboard {
    
    private String username;
    private String total_value;

    public String getusername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTotal_value() {
        return this.total_value;
    }

    public void setTotal_value(String total_value) {
        this.total_value = total_value;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("username", username)
            .add("total_value", total_value)
            .build();
    }



}

