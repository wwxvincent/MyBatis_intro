package com.vincent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//这个个 annotation 代替了 getter setter 和 toString
public class User {
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String address;


}
