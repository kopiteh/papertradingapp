package com.zenapp.papertrading.repository;

import static com.zenapp.papertrading.repository.SQL.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.zenapp.papertrading.models.Asset;
import com.zenapp.papertrading.models.Leaderboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class CoinAPIRepo {
    
    @Autowired
    private JdbcTemplate template; 

    public int[] batchUpdateListings(List<Asset> assets){
        List<Object[]> params = assets.stream()
            .map(asset -> new Object[] {
                asset.getPrice_usd(),
                asset.getAsset_id()
            }).collect(Collectors.toList());
        
        int added[] = template.batchUpdate(UPDATE_ASSET_LISTINGS, params);

        return added;
    }

    public List<Leaderboard> getLeaderboard(){ 

        final List<Leaderboard> result = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(GET_LEADERBOARD); 

        while (rs.next()){
            Leaderboard leaderboard = new Leaderboard(); 
            leaderboard.setName(rs.getString("name"));
            leaderboard.setTotal_value(rs.getString("total_value"));
            result.add(leaderboard); 
        }
        
        return (Collections.unmodifiableList(result));
    }

}
