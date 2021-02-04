package com.gallery.photo.model.dto;

import com.gallery.photo.model.Gallery;
import com.gallery.photo.model.Role;

import java.util.Set;

public class UserDTO {


    private Long id;
    private String userName;
    private String password;
    private String email;
    private Set<Role> roles;
    private Gallery gallery;


    public UserDTO(Long id, String userName, String password, String email, Set<Role> roles, Gallery gallery) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.gallery = gallery;
    }

    public UserDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }
}
