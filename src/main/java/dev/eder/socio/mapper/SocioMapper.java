package dev.eder.socio.mapper;

import dev.eder.socio.dto.SocioRequest;
import dev.eder.socio.dto.SocioResponse;
import dev.eder.socio.entity.Socio;

public final class SocioMapper {

    private SocioMapper(){
    }

    public static Socio toEntity(SocioRequest request){
        Socio socio = new Socio();
        actualizarEntidad(request, socio);
        return socio;
    }

    public static void actualizarEntidad(SocioRequest request, Socio socio){
        socio.setDni(request.dni().trim());
        socio.setNombres(request.nombres().trim());
        socio.setApellidos(request.apellidos().trim());
        socio.setEmail(normalizarEmail(request.email()));
        socio.setPlan(request.plan());
        socio.setMesesContratados(request.mesesContratados());
    }

    public static SocioResponse toResponse(Socio socio){
        return SocioResponse.builder()
        .id(socio.getId())
        .dni(socio.getDni())
        .email(socio.getEmail())
        .descripcion(socio.getNombres() + socio.getApellidos() + " - " + socio.getPlan())
        .mesesContratados(socio.getMesesContratados())
        .fechaRegistro(socio.getFechaRegistro())
        .build();
    }

    public static String normalizarEmail(String email){
        return email.trim().toLowerCase();
    }

}
