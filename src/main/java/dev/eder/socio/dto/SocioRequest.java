package dev.eder.socio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SocioRequest(

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^\\d{8}$", message = "El DNI tiene que tener 8 digitos")
    String dni,
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre debe tener maximo de 100 caracteres")
    String nombres,
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100, message = "El apellido debe tener maximo de 100 caracteres")
    String apellidos,
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato debe de estar en Email")
    @Size(max = 100, message = "El email debe de tener maximo de 100 caracteres")
    String email,
    @NotBlank(message = "El plan es obligatorio")
    @Size(max = 30, message = "El plan debe de tener maximo de 30 caracteres")
    String plan,
    @NotNull(message = "Los meses contratados son obligatorio")
    @Min(value = 1, message = "Los meses contratados minimo es de 1")
    @Max(value = 12, message = "Los meses contratados maximo es de 12")
    Integer mesesContratados
) {

}
