package com.codigo.persistencia.repository;

import com.codigo.persistencia.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long> {
    Optional<EstudianteEntity> findByNumDocumento(String numDoc);
    boolean existsByNumDocumento(String numDoc);

    List<EstudianteEntity> findAllByEstado(int estado);


    //SQL NATIVO
    //JPQL NATIVO

}
