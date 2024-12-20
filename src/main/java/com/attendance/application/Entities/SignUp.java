package com.attendance.application.Entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class SignUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer a_id;
    @Column
    private LocalDate date;
    private String SignIn;
    private String signOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    
    public String getSignIn() {
        return SignIn;
    }
    public void setSignIn(String signIn) {
        SignIn = signIn;
    }
    public String getSignOut() {
        return signOut;
    }
    public void setSignOut(String signOut) {
        this.signOut = signOut;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "SignUp [a_id=" + a_id + ", date=" + date + ", SignIn=" + SignIn + ", signOut=" + signOut + ", user="
                + user + "]";
    }

    

}
// This is attendance class
