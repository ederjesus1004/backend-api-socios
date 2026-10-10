package dev.eder.socio.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record SocioResponse(
    Long id,
    String dni,
    String email,
    String descripcion,
    Integer mesesContratados,
    LocalDateTime fechaRegistro
) {

}
