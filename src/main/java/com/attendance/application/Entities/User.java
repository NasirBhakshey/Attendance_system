package com.attendance.application.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer U_id;
    private String U_name;
    private String U_pass;
    private String email;
    private String U_phoneNo;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<SignUp> signUp;


    public Integer getU_id() {
        return U_id;
    }
    public void setU_id(Integer u_id) {
        U_id = u_id;
    }
    public void setU_name(String u_name) {
        U_name = u_name;
    }
    public String getU_name() {
        return U_name;
    }
    public String getU_pass() {
        return U_pass;
    }
    public void setU_pass(String u_pass) {
        U_pass = u_pass;
    }
    public String getU_phoneNo() {
        return U_phoneNo;
    }
    public void setU_phoneNo(String u_phoneNo) {
        U_phoneNo = u_phoneNo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<SignUp> getSignUp() {
        return signUp;
    }
    public void setSignUp(List<SignUp> signUp) {
        this.signUp = signUp;
    }

}
