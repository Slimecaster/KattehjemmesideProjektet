package com.example.kattehjemmesideprojektet.Repository;

import com.example.kattehjemmesideprojektet.Model.Cat;
import com.example.kattehjemmesideprojektet.Model.User;
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

    public User createUser (User user) {
        if (user.getUserId()==null) {
            sql= "INSERT INTO user(username,password,email,phoneNumber) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhoneNumber());
        } else {
            sql = "update user set username=?,password=?,email=?,phoneNumber=? where id=?";
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
        }
        return user;
    }

    public Cat createCat (Cat cat) {
        if (cat.getCatId()==null) {
            sql= "INSERT INTO cat(race,name,age,weight,owner) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql,cat.getRace(),cat.getName(),cat.getAge(),cat.getWeight(),cat.getOwner());
        } else {
            sql = "update cat set race=?,name=?,age=?,weight=?,owner=? where id=?";
            jdbcTemplate.update(sql, cat.getRace(), cat.getName(), cat.getAge(), cat.getWeight(), cat.getOwner());
        }
        return cat;
    }

    public void deleteUserById(Long id) {
        sql = "delete from user where id=?";
        jdbcTemplate.update(sql,id);
    }

    public void deleteCatById(Long id) {
        sql = "delete from cat where id=?";
        jdbcTemplate.update(sql,id);
    }

    public Optional<User> findUserById(Long id){
        sql= "select * from user where id=?";
        User user =jdbcTemplate.queryForObject(sql, new Object[]{id},userRowMapper());
        return Optional.ofNullable(user);
    }

    public List<User> findAllUsers(){
        sql= "select * from user";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public Optional<Cat> findCatByUser(Long id){
        sql="SELECT * from cat inner join user on cat.owner = user.userId where userId=?";
        Cat cat=jdbcTemplate.queryForObject(sql,new Object[]{id},catRowMapper());
        return Optional.ofNullable(cat);
    }

    public Optional<Cat> findCatByCatId(Long id){
        sql= "select * from cat where id=?";
        Cat cat= jdbcTemplate.queryForObject(sql,new Object[]{id},catRowMapper());
        return Optional.ofNullable(cat);
    }

    public List<Cat> findAllCats(){
        sql= "select * from cat";
        return jdbcTemplate.query(sql, catRowMapper());
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
            cat.setOwner(rs.getInt("owner"));
            return cat;
        };
    }

}
