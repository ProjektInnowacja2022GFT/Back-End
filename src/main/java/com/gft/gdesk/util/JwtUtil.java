package com.gft.gdesk.util;

import com.gft.gdesk.security.MyUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtUtil {

    private final String SECRET_KEY = "secret123412398";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}" + e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}" + e.getMessage());
        }
        return null;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", List.of(userDetails.getAuthorities()));
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, MyUserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}" + e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}" + e.getMessage());
        }
        return false;
    }
}