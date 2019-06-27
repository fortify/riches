package com.fortify.samples.riches.model;

public class Role
{
    private Long id;
    private String username;
    private String rolename;

    public Role() {
    }
    
    public Role(Long id, String username, String rolename) {
        this.id = id;
        this.username = username;
        this.rolename = rolename;
    }

    public Role(String username, String rolename) {
        this.username = username;
        this.rolename = rolename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
