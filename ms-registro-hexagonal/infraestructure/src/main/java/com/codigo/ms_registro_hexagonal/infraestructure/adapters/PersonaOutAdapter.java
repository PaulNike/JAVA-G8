package com.codigo.ms_registro_hexagonal.infraestructure.adapters;

import com.codigo.ms_registro_hexagonal.domain.aggregates.dto.PersonaDto;
import com.codigo.ms_registro_hexagonal.domain.aggregates.response.ResponseReniec;
import com.codigo.ms_registro_hexagonal.domain.ports.out.PersonaServiceOut;
import com.codigo.ms_registro_hexagonal.infraestructure.entity.PersonaEntity;
import com.codigo.ms_registro_hexagonal.infraestructure.mapper.PersonaMapper;
import com.codigo.ms_registro_hexagonal.infraestructure.repository.PersonaRepository;
import com.codigo.ms_registro_hexagonal.infraestructure.rest.ReniecClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class PersonaOutAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final ReniecClient reniecClient;
    private final PersonaMapper personaMapper;

    @Value("${token.api}")
    private String token;
    @Override
    public PersonaDto crearPersonaOut(String dni) {
        PersonaEntity personaEntity = getEntity(dni);
        if(!personaEntity.getNumDoc().isEmpty()){
            PersonaEntity dato = personaRepository.save(personaEntity);
            return  personaMapper.mapToDto(dato);
        }else {
            throw new RuntimeException("ERROR AL REGISTRAR A LA PERSONA");
        }
    }

    private PersonaEntity getEntity(String dni){
        PersonaEntity personaEntity = new PersonaEntity();
        ResponseReniec responseReniec = execute(dni);
        if(Objects.nonNull(responseReniec)){
            personaEntity.setNombres(responseReniec.getNombres());
            personaEntity.setApellidos(responseReniec.getApellidoPaterno() + " "
                    + responseReniec.getApellidoMaterno());
            personaEntity.setNumDoc(responseReniec.getNumeroDocumento());
            personaEntity.setTipoDoc(responseReniec.getTipoDocumento());
            personaEntity.setEstado(1);
            personaEntity.setUsuaCrea("Prodriguez");
            personaEntity.setDateCrea(new Timestamp(System.currentTimeMillis()));
        }
        return personaEntity;
    }

    private ResponseReniec execute(String dni){
        String head = "Bearer "+token;
        return reniecClient.getInfoReniec(dni,head);
    }


}
