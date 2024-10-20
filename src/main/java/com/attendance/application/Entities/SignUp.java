package com.attendance.application.Entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
    private LocalTime SignIn;
    private LocalTime SignOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
        this.SignIn = LocalTime.now();
        this.SignOut = LocalTime.now();
    }

    public LocalTime getSignIn() {
        return SignIn;
    }

    public void setSignIn(LocalTime signIn) {
        SignIn = signIn;
    }

    public LocalTime getSignOut() {
        return SignOut;
    }

    public void setSignOut(LocalTime signOut) {
        SignOut = signOut;
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
        return "SignUp [a_id=" + a_id + ", date=" + date + ", SignIn=" + SignIn + ", SignOut=" + SignOut + ", user="
                + user + "]";
    }

    public Optional<SignUp> orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

    

}
// This is attendance class
