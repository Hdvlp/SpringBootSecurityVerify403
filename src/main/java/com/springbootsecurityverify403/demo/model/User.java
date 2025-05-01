package com.springbootsecurityverify403.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "userstable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition="int", nullable = false)
    private int id;

    @Column(name = "email", columnDefinition="nvarchar(320)", unique = true, nullable = false)
    private String email;

    @Column(name = "first_name", columnDefinition="nvarchar(80)", unique = false, nullable = false)
    private String firstname;

    @Column(name = "last_name", columnDefinition="nvarchar(80)", unique = false, nullable = false)
    private String lastname;

    @Column(name = "password", columnDefinition="nvarchar(80)", unique = false, nullable = false)
    private String password;

    @Column(name = "role", columnDefinition="nvarchar(60)", unique = false, nullable = false)
    private String role;


    public User (){}

    public User (String email, String lastname, String firstname){
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public int getId() { return id; }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }




    public String toString(){
        return this.getId() + "|" +
                this.getEmail() + "|" +
                this.getLastname() + "|" +
                this.getFirstname() + "|" +
                this.getRole();
    }

}
