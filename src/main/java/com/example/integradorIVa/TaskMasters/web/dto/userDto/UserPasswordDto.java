package com.example.integradorIVa.TaskMasters.web.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserPasswordDto {

    @NotBlank
    @Size(min = 6, max = 20)
    private String currentlyPassword;
    @NotBlank
    @Size(min = 6, max = 20)
    private String newPassword;
    @NotBlank
    @Size(min = 6, max = 20)
    private String checkPassword;
    
}
