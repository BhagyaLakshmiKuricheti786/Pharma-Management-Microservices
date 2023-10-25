package com.ctel.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${app.secret}")
	private String secret;
	
//	STEP:1 Generate Token
//	------------------------------------------------
	public String generateToken(String subject)
	{
		return Jwts.builder().setSubject(subject).setIssuer("bhagya").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(1500000)))
				.signWith(SignatureAlgorithm.HS512,secret.getBytes()).compact();
	}
	
//	STEP:2   Read Claims
//	------------------------------------------------------
	public Claims getClaims(String token)
	{
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}
	
//	STEP:3   Read Expiration Date
//	-------------------------------------------
	public Date getExpDate(String token)
	{
		return getClaims(token).getExpiration();
	}

	
//	STEP:4   Read Subject/Username/EmailId
//	----------------------------------------------
	public String getEmailId(String token)
	{
		return getClaims(token).getSubject();
	}

//	STEP:5   Validate Expiration Date
//	----------------------------------------
	public boolean isTokenExp(String token)
	{
		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
//	STEP:6 Validate Username/EmailId in token and Database along with Expiration
//	----------------------------------------------------------------------------------------
     public boolean validateToken(String token, String emailId)
     {
    	 String tokenUsername = getEmailId(token);
    	 return (emailId.equals(tokenUsername) && !isTokenExp(token));
     }
}
