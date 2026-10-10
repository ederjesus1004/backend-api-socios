package dev.eder.socio.service;

import java.util.List;

import dev.eder.socio.dto.SocioRequest;
import dev.eder.socio.dto.SocioResponse;

public interface SocioService {

    List<SocioResponse> listar();

    SocioResponse obtenerPorId(Long id);

    SocioResponse crear(SocioRequest request);

    SocioResponse actualizar(Long id, SocioRequest request);

    void eliminar(Long id);
}
