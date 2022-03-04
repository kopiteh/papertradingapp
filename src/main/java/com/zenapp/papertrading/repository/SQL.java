package com.zenapp.papertrading.repository;

public class SQL {
    
    // * Update the asset table with data from API call. 
    public static final String UPDATE_ASSET_LISTINGS =
        "UPDATE listings SET price_usd = ? WHERE asset_id = ?";

    // * Joining different table to derive the leaderboard 
    public static final String GET_LEADERBOARD = 
        """
        SELECT d.username, ROUND(SUM(c.value),2) AS total_value
        FROM
            (
                SELECT a.fk_uid, a.asset_id, SUM(a.quantity) AS quantity, (SUM(a.quantity) * b.price_usd) as value 
                FROM users_holding a
                JOIN listings b
                ON a.asset_id = b.asset_id
                GROUP BY a.fk_uid, a.asset_id
            ) c
        JOIN users d
        ON c.fk_uid = d.uid
        GROUP BY d.username
        ORDER BY total_value DESC
        """;


}
