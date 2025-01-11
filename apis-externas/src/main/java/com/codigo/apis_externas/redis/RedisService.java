package com.codigo.apis_externas.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public void guardarEnRedis(String clave,
                               String valor,
                               int expiracion){
        //guardando la info en redis
        stringRedisTemplate.opsForValue().set(clave,valor);
        //asignando TTL
        stringRedisTemplate.expire(clave,expiracion, TimeUnit.SECONDS);
    }

    public String getDataDesdeRedis(String clave){
        return stringRedisTemplate.opsForValue().get(clave);
    }

    public void borrarDato(String clave){
        stringRedisTemplate.delete(clave);
    }

}
