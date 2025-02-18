package com.codigo.ms_seguridad.service.impl;

import com.codigo.ms_seguridad.aggregates.constants.Constants;
import com.codigo.ms_seguridad.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
public class JwtServiceImpl implements JwtService {

    @Value("${key.signature}")
    private String keySignature;
    @Override
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    //ACCESS TOKEN
    @Override
    public String generateToken(UserDetails userDetails) {
        log.info("Ejecutando - generateToken - ");
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(addClaim(userDetails))
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 120000))
                .claim("type", Constants.ACCESS)  //Claim agregado manual durante la generación
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())
        && !isTokenExpired(token));
    }

    //REFRESH TOKEN
    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1200000))
                .claim("type", Constants.REFRESH)
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean isRefreshToken(String token) {
        Claims claims = extractAllClaims(token);
        String tokenType = claims.get("type",String.class);
        return Constants.REFRESH.equalsIgnoreCase(tokenType);
    }

    //METODO QUE GENERA UNA LLAVE PARA FIRMAR LOS TOKENS
    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode(keySignature);
        return Keys.hmacShaKeyFor(key);
    }
    //METODO PARA EXTRAER EL PAYLOAD(CLAIMS) DEL TOKEN
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build()
                .parseClaimsJws(token).getBody();
    }
    //METODO PARA OBTENER UN ELEMENTO (ATRIBUTO) DEL PAYLOAD (CLAIMS)
    private <T> T extractClaim(String token,
                                Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(extractAllClaims(token));
    }
    //METODO PARA VALIDAR SI EL TOKEN ESTA EXPIRADO (TRUE ==  TOKEN EXPIRO | FALSE == TOKEN VALIDO)
    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Map<String, Object> addClaim(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.CLAVE_AccountNonLocked,userDetails.isAccountNonLocked());
        claims.put(Constants.CLAVE_AccountNonExpired,userDetails.isAccountNonExpired());
        claims.put(Constants.CLAVE_CredentialsNonExpired,userDetails.isCredentialsNonExpired());
        claims.put(Constants.CLAVE_Enabled,userDetails.isEnabled());
        claims.put("TipoUsuario",userDetails.getAuthorities()
                .stream().map(rol -> rol.getAuthority())
                .findFirst()
                .orElse("NOT_VALUE"));

        return claims;
    }
}
