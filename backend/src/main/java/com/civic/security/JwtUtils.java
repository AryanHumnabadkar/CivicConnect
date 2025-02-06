package com.civic.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j //Causes lombok to generate a logger field. 
public class JwtUtils {
	
	@Value("${SECRET_KEY}")
	private String jwtSecret;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;

	private SecretKey key; //actual key used to encode //?dk if SecretKey or Public Key
	
	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}
	
	//methods : generateToken, validateToken, getUserNameFromJwtToken, getAuthoritiesFromClaims
	
	public String generateToken(Authentication authObj) {
		log.info("genarting token "+ authObj);
		
		CustomUserDetails userPrincipal = (CustomUserDetails) authObj.getPrincipal();
		
		//create JWT : userName,userId?, issued at ,exp date,digital signature
		
		return Jwts.builder()
				.subject(userPrincipal.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime() + jwtExpirationMs))
				.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				.signWith(key, SIG.HS512)
				.compact();

	}
	
	// this method will be invoked by our custom JWT filter
	public String getUserNameFromJwtToken(Claims claims) {
		return claims.getSubject();
	}
		
	public Claims validateJwtToken(String jwtToken) {
		// try {
		Claims claims = Jwts.parser().verifyWith(key).build()
				.parseSignedClaims(jwtToken).getPayload();
		return claims;		
	}
	
	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		String authorityString = authorities.stream().
				map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		System.out.println(authorityString);
		return authorityString;
	}
		
	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}
	
	
}
