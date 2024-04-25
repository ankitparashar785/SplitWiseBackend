package com.app.splitwise.dtos;

import jakarta.persistence.Access;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRequest {
    private String email;
    private String password;
}
