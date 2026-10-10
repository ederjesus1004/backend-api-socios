package dev.eder.socio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.eder.socio.dto.SocioRequest;
import dev.eder.socio.dto.SocioResponse;
import dev.eder.socio.entity.Socio;
import dev.eder.socio.exception.RecursoDuplicadosException;
import dev.eder.socio.exception.RecursoNoEncontradoException;
import dev.eder.socio.mapper.SocioMapper;
import dev.eder.socio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocioServiceImpl implements SocioService {

    private final SocioRepository socioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SocioResponse> listar() {
        return socioRepository.findAll()
                .stream()
                .map(SocioMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SocioResponse obtenerPorId(Long id) {
        return SocioMapper.toResponse(buscarSocio(id));
    }

    @Override
    public SocioResponse crear(SocioRequest request) {
        String email = SocioMapper.normalizarEmail(request.email());
        if (socioRepository.existsByDni(request.dni())) {
            throw new RecursoDuplicadosException("Ya existe un socio con este DNI" + request.dni());
        }
        if (socioRepository.existsByEmail(email)) {
            throw new RecursoDuplicadosException("Ya existe un socio con este correo: " + email);
        }
        Socio socio = SocioMapper.toEntity(request);
        return SocioMapper.toResponse(socioRepository.save(socio));
    }

    @Override
    @Transactional
    public SocioResponse actualizar(Long id, SocioRequest request) {
        Socio socio = buscarSocio(id);
        String email = SocioMapper.normalizarEmail(request.email());
        if (socioRepository.existsByDniAndIdNot(request.email(), id)) {
            throw new RecursoNoEncontradoException("Ya existe un socio con este DNI " + request.email());
        }
        if (socioRepository.existsByEmailAndIdNot(email, id)) {
            throw new RecursoNoEncontradoException("Ya existe un socio con este email" + email);
        }

        SocioMapper.actualizarEntidad(request, socio);
        return SocioMapper.toResponse(socioRepository.save(socio));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Socio socio = buscarSocio(id);
        socioRepository.delete(socio);
    }

    private Socio buscarSocio(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el socio con id" + id));
    }
}
