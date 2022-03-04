package com.zenapp.papertrading.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    
    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User populate(SqlRowSet rs){
        final User u = new User ();
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        return u; 
    }

}
