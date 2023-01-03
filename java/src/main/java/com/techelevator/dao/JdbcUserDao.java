package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }

	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			return null;
		}
	}

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password, String role) {
        String insertUserSql = "insert into users (username,password_hash,role) values (?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole) == 1;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }


    /* STUFF I ADDED IS BELOW HERE */

    @Override
    public String getRoleById(int id) {
        String sql = "SELECT role FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return results.getString("role");
        } else {
            return null;
        }
    }


    @Override
    public List<User> getRentersByLandlordId(int landlordId) {
        List<User> users = new ArrayList<>();

        String sql = "select user_id, username, password_hash, role " +
                    "from users " +
                    "join property on user_id = renter_id " +
                    "where landlord_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, landlordId);

        while (rowSet.next()) {
            users.add(mapRowToUser(rowSet));
        }

        return users;
    }

    @Override
    public List<User> getAllWorkers() {
        List<User> workers = new ArrayList<>();

        String sql = "select * from users " +
                "where role = 'ROLE_WORKER';";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        while (rowSet.next()) {
            workers.add(mapRowToUser(rowSet));
        }

        return workers;
    }

    @Override
    public User getUserByIdAndRole(int id, String role) {

        //TO DO
        String sql = "SELECT * FROM users WHERE user_id = ? AND role = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id, role);

        if (rowSet.next()) {
            return mapRowToUser(rowSet);
        }

        return null;
    }

}
