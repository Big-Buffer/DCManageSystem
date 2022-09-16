package com.example.dcmanagesystem.bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String username;
    private String password;
    private Integer authority;
}
