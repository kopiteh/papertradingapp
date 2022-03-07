package com.zenapp.papertrading.models;


import jakarta.json.JsonObject;

public class TxnOrder {
    private String asset_id;
    private String price_usd;
    private String quantity;

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public static TxnOrder convert(JsonObject obj ){
        TxnOrder bo = new TxnOrder();
        bo.setAsset_id(obj.getString("asset_id"));
        bo.setPrice_usd(obj.getJsonNumber("price_usd").toString());
        bo.setQuantity(obj.getJsonNumber("quantity").toString());

        return bo;
    }


}
