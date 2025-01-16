package com.codigo.ms_registro_hexagonal.infraestructure.mapper;

import com.codigo.ms_registro_hexagonal.domain.aggregates.dto.PersonaDto;
import com.codigo.ms_registro_hexagonal.infraestructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public PersonaDto mapToDto(PersonaEntity personaEntity){
         return MODEL_MAPPER.map(personaEntity, PersonaDto.class);
    }

    public PersonaEntity mapToEntity(PersonaDto personaDto){
        return MODEL_MAPPER.map(personaDto, PersonaEntity.class);
    }
}
