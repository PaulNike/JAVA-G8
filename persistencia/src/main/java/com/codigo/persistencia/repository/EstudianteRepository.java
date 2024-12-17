package com.codigo.persistencia.repository;

import com.codigo.persistencia.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long> {
    Optional<EstudianteEntity> findByNumDocumento(String numDoc);

    boolean existsByNumDocumento(String numDoc);

    List<EstudianteEntity> findAllByEstado(int estado);



    //SQL NATIVO

    @Query(value = "Select e.* from estudiante e" +
            "join estudiante_curso ec on e.id = ec.estudiante_id " +
            "join curso c on c.id = ec.curso_id " +
            "where c.descripcion = :nombreCurso ", nativeQuery = true)
    List<EstudianteEntity> buscarEstudianteXCurso(@Param("nombreCurso") String nombreCurso);
    //JPQL NATIVO

}
