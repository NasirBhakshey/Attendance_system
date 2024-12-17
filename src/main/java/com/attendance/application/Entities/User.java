package com.attendance.application.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer U_id;
    private String U_name;
    @NotEmpty(message = "Password Cannot be null...")
    // @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "length must be 5")
    @Size(min = 5, message = "required")
    private String U_pass;
    private String email;
    private String U_phoneNo;

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

}
