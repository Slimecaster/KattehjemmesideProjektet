package com.example.kattehjemmesideprojektet.Repository;

import com.example.kattehjemmesideprojektet.Model.Cat;
import com.example.kattehjemmesideprojektet.Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KattehjemmesideRepository {
    private final JdbcTemplate jdbcTemplate;
    String sql;

    public KattehjemmesideRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User createUser (User user) {
        if (user.getUserId()==null) { sql= "INSERT INTO user(username,password,email,phoneNumber) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhoneNumber());
        } else {
            sql = "update user set username=?,password=?,email=?,phoneNumber=? where id=?";
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
        }
        return user;
    }

    public Cat createCat (Cat cat) {
        if (cat.getCatId()==null) {
            sql= "INSERT INTO cat(race,name,age,weight) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql,cat.getRace(),cat.getName(),cat.getAge(),cat.getWeight());
        } else {
            sql = "update user set race=?,name=?,age=?,weight=? where id=?";
            jdbcTemplate.update(sql, cat.getRace(), cat.getName(), cat.getAge(),cat.getWeight());
        }
        return cat;
    }


}
