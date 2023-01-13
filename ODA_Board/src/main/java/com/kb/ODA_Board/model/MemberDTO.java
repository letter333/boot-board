package com.kb.ODA_Board.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String role;
}
