package com.example.xiamin.myapplication;

/**
 * Created by Xiamin on 2016/6/20.
 */
public class userinfo {
    private String username;
    private String password;

    public userinfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public userinfo() {
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {

        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
