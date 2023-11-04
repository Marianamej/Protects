package com.gamertx.domain.service;

import com.gamertx.persistence.entity.users_view.Usuario;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@PropertySource("classpath:application-secrets.properties")
public class JwtService {
    @Value("${security.jwt.expiration-minutes}")
    private Long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    private final Date issueAt;
    private final Date expiration;

    @Autowired
    public JwtService(@Value("${security.jwt.expiration-minutes}") Long expirationMinutes,
                      @Value("${security.jwt.secret-key}") String secretKey) {
        this.EXPIRATION_MINUTES = expirationMinutes;
        this.SECRET_KEY = secretKey;
        this.issueAt = new Date(System.currentTimeMillis());
        this.expiration = new Date(System.currentTimeMillis() + (EXPIRATION_MINUTES * 60 * 1000));
    }

    public String generateToken(Usuario usuario, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(usuario.getEmail())
                .issuedAt(issueAt)
                .expiration(expiration)
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey(){
        byte [] secretsAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println("Mi clave es : "+ new String(secretsAsBytes));
        return Keys.hmacShaKeyFor(secretsAsBytes);
    }
}
