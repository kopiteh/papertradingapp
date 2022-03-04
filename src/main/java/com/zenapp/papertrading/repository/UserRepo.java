package com.zenapp.papertrading.repository;

import java.util.Optional;

import com.zenapp.papertrading.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    
    private static final String SQL_SELECT_USER_BY_USERNAME =
		"select * from users where username = ?";

	private static final String SQL_COMPARE_PASSWORDS_BY_USERNAME = 
		"select count(*) as user_count from users where username = ? and password = sha1(?)";

    @Autowired
    private JdbcTemplate template;

    public Optional<User> findUserByName(String username) {
		final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_USER_BY_USERNAME, username);
		if (rs.next())
			return Optional.of(User.populate(rs));
		return Optional.empty();
	}

	public boolean validateUser(String username, String password) {
		final SqlRowSet rs = template.queryForRowSet(SQL_COMPARE_PASSWORDS_BY_USERNAME, username, password);
		if (!rs.next())
			return false;

		return rs.getInt("user_count") > 0;

    }

	public boolean addUser(String username, String email, String password){

		int added = template.update(
				"INSERT INTO users (username, email, password) VALUES (?,?,SHA1(?))",
				username, email, password);

		return added > 0;
	}
}
