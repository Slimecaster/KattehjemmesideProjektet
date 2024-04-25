package com.example.kattehjemmesideprojektet.Service;

import com.example.kattehjemmesideprojektet.Model.Cat;
import com.example.kattehjemmesideprojektet.Model.User;
import com.example.kattehjemmesideprojektet.Repository.KattehjemmesideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KattehjemmesideService {

    @Autowired
    private KattehjemmesideRepository kattehjemmesideRepository;
    private User user;

    public void createUser (User user){
        kattehjemmesideRepository.createUser(user);
    }

    public void createCat (Cat cat){
        kattehjemmesideRepository.createCat(cat);
    }

    public void deleteUserById (Long id){
        kattehjemmesideRepository.deleteUserById(id);
    }

    public void deleteCatById (Long id){
        kattehjemmesideRepository.deleteCatById(id);
    }

    public Optional<User> findUserById(Long id){
        return kattehjemmesideRepository.findUserById(id);
    }
    public List<User> findAllUsers(){
        return kattehjemmesideRepository.findAllUsers();
    }

    public Optional<Cat> findCatByUser(Long id){
        return kattehjemmesideRepository.findCatByUser(id);
    }

    public Optional<Cat> findCatById(Long id){
        return kattehjemmesideRepository.findCatById(id);
    }

    public List<Cat> findAllCats(){
        return kattehjemmesideRepository.findAllCats();
    }

    //Tilføjelse af autentifikationsmetode:
    //Metoden .isPresent() i Java's Optional klasse er brugt til at tjekke,
    // om der er en værdi til stede i Optional objektet.
    // Denne metode returnerer en boolesk værdi—true hvis der er en værdi til stede, og false hvis Optional er tom

    public boolean authenticateUser(String username, String password) {

        return kattehjemmesideRepository.findUserByUsernameAndPassword(username, password).isPresent();

    }

}
