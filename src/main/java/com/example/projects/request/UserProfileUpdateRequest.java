package com.example.projects.request;

public class UserProfileUpdateRequest {
        private String Username;
        private String email;
        private String phoneNumber;

    public UserProfileUpdateRequest(String username, String email, String phoneNumber) {
        Username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
