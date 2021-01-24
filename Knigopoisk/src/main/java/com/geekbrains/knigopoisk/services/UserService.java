package com.geekbrains.knigopoisk.services;

import com.geekbrains.knigopoisk.entities.User;
import com.geekbrains.knigopoisk.repositories.UserRerository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRerository userRerository;

    @Autowired
    public void setUserRerository(UserRerository userRerository){
        this.userRerository = userRerository;
    }

    public List<User> getAllUsers(){
        return (List<User>) userRerository.findAll();
    }

    public void saveUser(User user){
        userRerository.save(user);
    }

    public void deleteUser(Long id){
        userRerository.deleteById(id);
    }
}
