package com.example.integradorIVa.TaskMasters.web.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateDto {

    @NotBlank
    @Email(message = "formato do e-mail está invalido")
    private String username;
    @NotBlank
    @Size(min = 6, max = 10)
    private String password;
}
