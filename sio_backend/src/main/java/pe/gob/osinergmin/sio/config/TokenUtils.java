package pe.gob.osinergmin.sio.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenUtils {
	
	private static String ACCESS_TOKEN_SECRET;
    private static Long ACCESS_TOKEN_VALIDITY_SECONDS;

    @Value("${access.token.secret}")
    public void setAccessTokenSecret(String accessTokenSecret) {
        ACCESS_TOKEN_SECRET = accessTokenSecret;
    }

    @Value("${access.token.validity.seconds}")
    public void setAccessTokenValiditySeconds(Long accessTokenValiditySeconds) {
        ACCESS_TOKEN_VALIDITY_SECONDS = accessTokenValiditySeconds;
    }
	
	public static String createToken(Integer idUsuario, String email) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		Map<String,Object> extra = new HashMap<>();
		extra.put("userId", idUsuario);
		
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				.compact();
	}
	
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email = claims.getSubject();
			
			List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
		    authoritiesList.add(new SimpleGrantedAuthority("ROLE_"));
			return new UsernamePasswordAuthenticationToken(email,null,authoritiesList);
		}catch(JwtException e) {
			return null;
		}
		
	}

	public static String getAccessTokenSecret() {
		return ACCESS_TOKEN_SECRET;
	}
	
	public static String getEmailByToken(String token) {
		String email = "";
		
		try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getAccessTokenSecret().getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            email = claims.getSubject();

        } catch (JwtException e) {

        }
        return email;
    }
	
	public static Integer getUserIdFromToken(String token) {
	    try {
	        Claims claims = Jwts.parserBuilder()
	                .setSigningKey(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
	                .build()
	                .parseClaimsJws(token)
	                .getBody();

	        return claims.get("userId", Integer.class);
	    } catch (Exception e) {
	        return null;
	    }
	}

}
