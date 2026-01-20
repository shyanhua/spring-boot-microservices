package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.model.enums.CustomerStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Phone must be valid")
    @Pattern(regexp = "^[0-9-]+$")
    private String phone;
    private String address;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    private CustomerStatus status;
}
