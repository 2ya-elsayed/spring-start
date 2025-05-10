package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;

    @NotEmpty(message = "Username is required")
    private String userName;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password length must be 6 at min")
    private String password;

    @NotEmpty(message = "Phone number is required")
    @Size(min = 11, max = 11, message = "Invalid phone number")
    private String phoneNumber;

    public AccountDto() {}

    public AccountDto(Long id, String password, String phoneNumber, String userName) {
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
