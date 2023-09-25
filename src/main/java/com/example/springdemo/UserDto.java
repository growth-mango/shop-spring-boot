package com.example.springdemo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
public class UserDto {

    private String name;
    private Integer age;
}
