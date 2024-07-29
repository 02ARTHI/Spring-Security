package com.telusko.SpringScProj1.service;



import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService 
{
	//public static final String SECRET="shjdbcsjkhdcb aisugd iusag isugf f";
	
	public String getTheSecretKey()
	{
		try
		{
			KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
			SecretKey secretKey = key.generateKey();
			return Base64.getEncoder().encodeToString(secretKey.getEncoded());
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error in generating key");
		}
		
	}
	
	public String generateToken(String name)
	{
		
	
		Map<String, Object> claims=new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(name)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() +1000*60*5))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
				
		    
	}

	private Key getKey() {
		byte[] b = Decoders.BASE64.decode(getTheSecretKey());
		return Keys.hmacShaKeyFor(b);
	}

}
