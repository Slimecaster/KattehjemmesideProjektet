package com.example.kattehjemmesideprojektet.Repository;

import com.example.kattehjemmesideprojektet.Model.Cat;
import com.example.kattehjemmesideprojektet.Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KattehjemmesideRepository {
    private final JdbcTemplate jdbcTemplate;
    String sql;

    public KattehjemmesideRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a user or updates their information depending on whether the userId already exists
     * @param user the user that will be created
     * @return the user object created and stored in the database
     * @throws DataAccessException if the user is not found in the database
     */

    public User createUser (User user) {
        try {
            if (user.getUserId() == null) {
                sql = "INSERT INTO user(username,password,email,phoneNumber) VALUES (?,?,?,?)";
                jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
            } else {
                sql = "update user set username=?,password=?,email=?,phoneNumber=? where userId="+String.valueOf(user.getUserId());
                jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
            }
            return user;
        } catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while creating user",e);
        }
    }

    /**
     * Creates a cat or updates its information depending on whether the catId already exists
     * @param cat the cat that will be created
     * @return the cat object created and stored in the database
     * @throws DataAccessException if the cat is not found in the database
     */
    public Cat createCat (Cat cat) {
        try {
            if (cat.getCatId() == null) {
                sql = "INSERT INTO cat(race,name,age,weight,owner) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(sql, cat.getRace(), cat.getName(), cat.getAge(), cat.getWeight(), cat.getOwner());
            } else {
                sql = "update cat set race=?,name=?,age=?,weight=?,owner=? where catId="+String.valueOf(cat.getCatId());
                jdbcTemplate.update(sql, cat.getRace(), cat.getName(), cat.getAge(), cat.getWeight(), cat.getOwner());
            }
            return cat;
        } catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while creating cat",e);
        }
    }

    /**
     * Deletes a user and their cats by their userId
     * @param userId the userId of the user that will be deleted
     */
    public void deleteUserById(Long userId) {
        sql = "delete from user where userId =?";
        // sql = "DELETE user, cat FROM user INNER JOIN cat WHERE user.userId= cat.owner AND user.userId=?";
        jdbcTemplate.update(sql,userId);
    }



    /**
     * Deletes a cat by their catId
     * @param catId the catId of the cat that will be deleted
     */
    public void deleteCatById(Long catId) {
        sql = "delete from cat where catId=?";
        jdbcTemplate.update(sql,catId);
    }

    /**
     * Finds all information about a user by their userId
     * @param userId the userId of the user that will be found
     * @return the user and all their information
     * @throws DataAccessException if the user is not found
     */
    public Optional<User> findUserById(Long userId){
        try {
            sql= "select * from user where userId=?";
            User user =jdbcTemplate.queryForObject(sql, new Object[]{userId},userRowMapper());
            return Optional.ofNullable(user);
        }catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding user",e);
        }
    }
    //Kommentar til github
    /**
     * Finds a user by their email
     * @param email
     * @return the user found
     */

    public Optional<User> findUserByEmail(String email){
        try {
            sql= "select * from user where email=?";
            User user =jdbcTemplate.queryForObject(sql, new Object[]{email},userRowMapper());
            return Optional.ofNullable(user);
        }catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding user",e);
        }
    }

    /**
     * Finds a list of all users and their information in the database
     * @return a list of all information about all users in the database
     * @throws DataAccessException if the users are not found
     */
    public List<User> findAllUsers(){
        try {
            sql = "select * from user";
            return jdbcTemplate.query(sql, userRowMapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding all users",e);
        }
    }

    /**
     * Finds all information about a cat by their owners userId
     * @param userId the userId of the user that owns the cat
     * @return all information about the found cat
     * @throws DataAccessException if the cat is not found
     */
    public Optional<Cat> findCatByUser(Long userId){
        try {
            sql = "SELECT * from cat inner join user on cat.owner = user.userId where userId=?";
            Cat cat = jdbcTemplate.queryForObject(sql, new Object[]{userId}, catRowMapper());
            return Optional.ofNullable(cat);
        }catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding cat by their user",e);
        }
    }

    /**
     * Finds all information about a cat by its own catId
     * @param catId the catId of the cat that will be found
     * @return the cat found by their own id
     * @throws DataAccessException if the cat is not found
     */
    public Optional<Cat> findCatById(Long catId){
        try {
            sql = "SELECT * FROM cat WHERE catId=?";
            Cat cat = jdbcTemplate.queryForObject(sql, new Object[]{catId}, catRowMapper());
            return Optional.ofNullable(cat);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Return empty Optional if no cat is found
        } catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding cat", e);
        }
    }

    /**
     * Finds all cats and all their information
     * @return a list of all cats with their information
     * @throws DataAccessException if the cats are not found
     */
    public List<Cat> findAllCats(){
        try {
            sql = "select * from cat";
            return jdbcTemplate.query(sql, catRowMapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error accessing data while finding all cats",e);
        }
    }


    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getLong("userId"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("phoneNumber"));
            return user;
        };
    }
    private RowMapper<Cat> catRowMapper() {
        return (rs, rowNum) -> {
            Cat cat = new Cat();
            cat.setCatId(rs.getLong("catId"));
            cat.setRace(rs.getString("race"));
            cat.setName(rs.getString("name"));
            cat.setAge(rs.getInt("age"));
            cat.setWeight(rs.getDouble("weight"));
            cat.setOwner(rs.getLong("owner"));
            return cat;
        };
    }




}
