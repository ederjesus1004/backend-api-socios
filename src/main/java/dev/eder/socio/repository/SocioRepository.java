package dev.eder.socio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.eder.socio.entity.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long>{

    boolean existsByDni(String dni);
    boolean existsByDniAndIdNot(String dni, Long id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

}
