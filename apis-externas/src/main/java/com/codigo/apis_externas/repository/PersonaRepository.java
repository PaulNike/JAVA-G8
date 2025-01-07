package com.codigo.apis_externas.repository;

import com.codigo.apis_externas.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {


}
