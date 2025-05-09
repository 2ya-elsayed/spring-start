package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;
    private String userName;
    private String password;
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
