package org.example.Repository;

public class userRepo {

    public String getLoginStatus(String username, String password) {
        if (username != null && password != null) {
            if ("abdul".equals(username) && "abdul".equals(password)) {
                return "Login Success";
            } else {
                return "Login Failed";
            }

        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    public void sendEmailSms(Integer count) {
        System.out.println("Email send today is :: " + count);
    }
}
