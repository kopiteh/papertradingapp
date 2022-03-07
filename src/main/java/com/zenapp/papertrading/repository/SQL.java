package com.zenapp.papertrading.repository;

public class SQL {

    public static final String UPDATE_ASSET_LISTINGS = "UPDATE listings SET price_usd = ? WHERE asset_id = ?";

    public static final String GET_LEADERBOARD = """
            SELECT a.username, ROUND((a.balance + COALESCE(b.txn_balance,0)),2)as total_value
            FROM users a
            LEFT JOIN
            	(
                SELECT t.fk_username, SUM(t.quantity * l.price_usd) as txn_balance
            	FROM transactions t
            	LEFT JOIN listings l
            	on t.asset_id = l.asset_id
            	GROUP BY t.fk_username
                ) b
            ON a.username = b.fk_username;
            """;
    public static final String GET_MYPORTFOLIO = """              
            SELECT a.fk_username, a.asset_id, b.price_usd,  ROUND(SUM(a.quantity),2) as quantity, ROUND((SUM(a.quantity) * b.price_usd),2) as value
            FROM transactions a
            JOIN listings b
            ON a.asset_id = b.asset_id
            WHERE a.fk_username = ?
            GROUP BY a.fk_username, a.asset_id
            ORDER BY VALUE DESC;
            """;

}
