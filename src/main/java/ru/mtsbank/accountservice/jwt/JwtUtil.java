package ru.mtsbank.accountservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;


    private SecretKey init(){
        byte[] decode = Base64.getDecoder().decode(SECRET);
        SecretKey secretKey = new SecretKeySpec(decode, "HmacSHA256");
        return secretKey;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(init())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public String extractAccountId(String token) {
        log.info(token);
        log.info(init().toString());
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(init()).build().parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get("bankAccountId", String.class);
    }
}
