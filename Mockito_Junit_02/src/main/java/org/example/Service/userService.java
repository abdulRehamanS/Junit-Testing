package org.example.Service;

import org.example.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class userService {

    @Autowired
    private userRepo repo;

    public userService(userRepo repo) {
        this.repo = repo;
    }

    public String validateLogin(String username, String password) {
        return repo.getLoginStatus(username, password);
    }

    public void sendEmailSms() {
        repo.sendEmailSms(5);
    }
}
