package com.itp.factory.management.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itp.factory.management.enums.CommonStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserAddResource {

    @NotBlank(message = "{common.not-null}")
    @Size(max = 20, message = "{common-name.size}")
    private String type;


    @NotBlank(message = "{common.not-null}")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "{common.not-null}")
    @Size(max = 70, message = "{common-name.size}")
    private String name;

    private String address;

    private String dob;

    @NotBlank(message = "{common.not-null}")
    private String user_name;

    private String leavesremain;

    private String joineddate;

    private String salary;

    @NotBlank(message = "{common.not-null}")
    private String status;

    private String tenantId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
