package com.avaand.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.java.Log;


import java.util.UUID;

@Log
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_table")
public class Person {
    @NotNull
    @Id
    @Column(unique = true, name = "user_id", nullable = false)
    private String userId = UUID.randomUUID().toString();
    //@Email(message = "Please enter email format.")
    //@NotEmpty(message = "Please enter username.")
    private String username;
    //@NotEmpty(message = "Please enter password.")
    //@Min(value = 4, message = "Please password should be more than 4 digit or character.")
    private String password;
    //@IpAddress(message = "Provide valid Ip address.")
    private String ipAddress;
    //@MacAddress(message = "Provide valid Mac address.")
    private String macAddress;
}
