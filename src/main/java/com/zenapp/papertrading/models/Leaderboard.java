package com.zenapp.papertrading.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Leaderboard {
    
    private String name;
    private String total_value;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal_value() {
        return this.total_value;
    }

    public void setTotal_value(String total_value) {
        this.total_value = total_value;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("name", name)
            .add("total_value", total_value)
            .build();
    }



}

