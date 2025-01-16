package com.codigo.ms_registro_hexagonal.infraestructure.repository;

import com.codigo.ms_registro_hexagonal.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
