package com.codigo.apis_externas.service.impl;

import com.codigo.apis_externas.aggregates.constants.Constants;
import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.aggregates.response.ResponseBase;
import com.codigo.apis_externas.client.ClientReniec;
import com.codigo.apis_externas.entity.PersonaEntity;
import com.codigo.apis_externas.redis.RedisService;
import com.codigo.apis_externas.repository.PersonaRepository;
import com.codigo.apis_externas.service.ReniecService;
import com.codigo.apis_externas.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ReniecServiceImpl implements ReniecService {

    @Autowired
    private  ClientReniec clientReniec;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RedisService redisService;

    @Value("${token.api}")
    private String token;
    @Override
    public ReniecResponse buscarPorDni(String numDni) {
        ReniecResponse reniecResponse = new ReniecResponse();
        log.info("Buscando Informacion para el DNI: {}", numDni);
        //FLujo para validar la info con redis
        //1- Recuperar la info de redis
        String dataRedis = redisService.getDataDesdeRedis(Constants.REDIS_KEY_API_RENIEC+numDni);
        //Validar
        if(Objects.nonNull(dataRedis)){
            log.info("RECUPERANDO INFORMACIÓN DESDE REDIS PARA EL DNI: {}",numDni );
            reniecResponse = Util.convertirDesdeString(dataRedis,ReniecResponse.class);
        }else {
            log.info("RECUPERANDO INFORMACIÓN DESDE EL API DE RENIEC PARA EL DNI: {}",numDni );
            //Ejecutamos la consulta al api de reniec
            reniecResponse = execution(numDni);
            //Convierto el objeto a String
            String dataParaRedis = Util.convertirAString(reniecResponse);
            //Guardo en Redis
            redisService.guardarEnRedis(Constants.REDIS_KEY_API_RENIEC+numDni,
                    dataParaRedis,
                    60);
        }
        return reniecResponse;
    }

    @Override
    public ResponseBase<PersonaEntity> resgitrarPersona(String numDni) {
        log.info("Registrando persona con DNI: {}", numDni);
        //Aqui Ejecute la consulkta al servicio de reniec
        ReniecResponse consultaReniec = execution(numDni);

        if(consultaReniec == null){
            return buildErrorResponse(Constants.CODIGO_ERROR,Constants.MENSAJE_ERROR);
        }
        //Instancia para regitrar a la persona
        PersonaEntity personaEntity = buildPersonaEntity(consultaReniec);
        PersonaEntity personaGuardada = personaRepository.save(personaEntity);

        return buildSuccesResponse(personaGuardada);
    }

    private ResponseBase<PersonaEntity> buildSuccesResponse(PersonaEntity personaGuardada) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setCodigo(Constants.CODIGO_OK);
        responseBase.setMensaje(Constants.MENSAJE_OK);
        responseBase.setEntidad(Optional.of(personaGuardada));
        return responseBase;
    }

    private PersonaEntity buildPersonaEntity(ReniecResponse consultaReniec) {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setNombres(consultaReniec.getNombres());
        personaEntity.setApellidoPaterno(consultaReniec.getApellidoPaterno());
        personaEntity.setApellidoMaterno(consultaReniec.getApellidoMaterno());
        personaEntity.setTipoDocumento(consultaReniec.getTipoDocumento());
        personaEntity.setNumeroDocumento(consultaReniec.getNumeroDocumento());
        personaEntity.setDigitoVerificador(consultaReniec.getDigitoVerificador());
        //DATOS ADICIONALES DE LA ENTIDAD
        personaEntity.setEstado(Constants.ESTADO_ACTIVO);
        personaEntity.setUser_create(Constants.USER_CREATED);
        personaEntity.setDate_create(new Timestamp(System.currentTimeMillis()));
        return personaEntity;
    }

    private ResponseBase<PersonaEntity> buildErrorResponse(Integer codigoError, String mensajeError) {
        ResponseBase<PersonaEntity> responseBase = new ResponseBase<>();
        responseBase.setCodigo(Constants.CODIGO_ERROR);
        responseBase.setMensaje(Constants.MENSAJE_ERROR);
        responseBase.setEntidad(Optional.empty());
        return responseBase;
    }

    //EJECUTANDO EL API EXTERNA
    private ReniecResponse execution(String numDni){
        String tokenOk = "Bearer "+token;
        return clientReniec.getPersona(numDni,tokenOk);
    }
}
