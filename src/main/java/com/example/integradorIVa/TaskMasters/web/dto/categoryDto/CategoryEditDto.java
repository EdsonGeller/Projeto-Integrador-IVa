package com.example.integradorIVa.TaskMasters.web.dto.categoryDto;

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
public class CategoryEditDto {
    @NotBlank
    @Size(min = 1, max = 20)
    private String categoryName;
}
