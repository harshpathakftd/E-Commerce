package com.ex.smt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userdto {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private boolean isActive;
}
