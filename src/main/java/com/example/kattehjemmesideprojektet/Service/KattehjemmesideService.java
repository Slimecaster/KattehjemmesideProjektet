package com.example.kattehjemmesideprojektet.Service;

import com.example.kattehjemmesideprojektet.Model.User;
import com.example.kattehjemmesideprojektet.Repository.KattehjemmesideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KattehjemmesideService {

    @Autowired
    private KattehjemmesideRepository kattehjemmesideRepository;

    public void createUser (User user){
        kattehjemmesideRepository.createUser(user);
    }

}
