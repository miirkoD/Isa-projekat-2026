package rs.ac.singidunum.novisad.videoteka_projekat_isa.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(String username, String rola) {
		return Jwts.builder().subject(username).claim("rola", rola)
				.issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(getSigningKey()).compact();
	}
	
	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	public String exrtactRola(String token) {
		return getClaims(token).get("rola", String.class);
	}
	
	public boolean isTokenValid(String token) {
		try {
			getClaims(token);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	private Claims getClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build()
				.parseSignedClaims(token).getPayload();}
	
}
