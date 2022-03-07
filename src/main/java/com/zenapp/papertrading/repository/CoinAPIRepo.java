package com.zenapp.papertrading.repository;

import com.zenapp.papertrading.models.Asset;
import com.zenapp.papertrading.models.TxnOrder;
import com.zenapp.papertrading.models.Leaderboard;
import com.zenapp.papertrading.models.MyPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zenapp.papertrading.repository.SQL.*;

@Repository
public class CoinAPIRepo {

    @Autowired
    private JdbcTemplate template;

    //Updating the assets table with the latest price retrieved via API call
    public int[] batchUpdateListings(List<Asset> assets) {
        List<Object[]> params = assets.stream()
                .map(asset -> new Object[]{
                        asset.getPrice_usd(),
                        asset.getAsset_id()
                }).collect(Collectors.toList());

        int added[] = template.batchUpdate(UPDATE_ASSET_LISTINGS, params);

        return added;
    }

    // Retrieving the latest leaderboard.
    public List<Leaderboard> getLeaderboard() {

        final List<Leaderboard> result = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(GET_LEADERBOARD);

        while (rs.next()) {
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setUsername(rs.getString("username"));
            leaderboard.setTotal_value(rs.getString("total_value"));
            result.add(leaderboard);
        }

        return (Collections.unmodifiableList(result));
    }

    // Getting current portfolio for specific user.
    public List<MyPortfolio> getMyPortfolio(String username) {
        List<MyPortfolio> result = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(GET_MYPORTFOLIO, username);

        while (rs.next()) {
            MyPortfolio portfolio = new MyPortfolio();
            portfolio.setUsername(rs.getString("fk_username"));
            portfolio.setAsset_id(rs.getString("asset_id"));
            portfolio.setPrice_usd(rs.getFloat("price_usd"));
            portfolio.setQuantity(rs.getFloat("quantity"));
            portfolio.setValue(rs.getFloat("value"));

            result.add(portfolio);
        }

        return (Collections.unmodifiableList(result));

    }

    // Retrieve balance to see if buy order can proceed.
    public float checkBalance(String username){

        float balance;
        balance = template.queryForObject(
                "SELECT balance FROM users WHERE username = ?", Float.class, username
        );
        return balance;
    }

    // Add BUY tranasction
    public boolean addBuyOrder (final TxnOrder to, String username) {
        int added = template.update(
                "INSERT INTO transactions (asset_id, quantity, price_usd, fk_username) VALUES (?,?,?,?)",
                to.getAsset_id(), to.getQuantity(), to.getPrice_usd(), username
        );

        return added > 0;
    }
    // Add BUY tranasction
    public boolean addSellOrder (final TxnOrder to, String username){
        int added = template.update(
                "INSERT INTO transactions (asset_id, quantity, price_usd, fk_username) VALUES (?,?,?,?)",
                to.getAsset_id(), to.getQuantity(), to.getPrice_usd(), username
        );

        return added > 0;
    }
    // Update user balance after each transaction
    public boolean updateUserBalance(final TxnOrder to, String username){
        int added = template.update(
                "UPDATE users SET balance = balance - (? * ?) WHERE username = ?",
                to.getPrice_usd(), to.getQuantity(), username
        );

        return added > 0;
    }

}
