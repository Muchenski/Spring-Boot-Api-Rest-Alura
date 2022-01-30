package com.alura.model.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alura.model.domain.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.tempo-expiracao}")
	private Long tempoDeExpiracao;
	
	@Value("${jwt.segredo}")
	private String segredo;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer("API Alura") // Sistema que criou o token.
				.setSubject(usuario.getId().toString()) // Id do usuário do token.
				.setIssuedAt(new Date()) // Data de criação.
				.setExpiration(new Date(tempoDeExpiracao + new Date().getTime()))  // Data de expiração.
				.signWith(SignatureAlgorithm.HS256, segredo) // Tipo de criptografia e segredo.
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(segredo).parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public Long obterIdUsuario(String token) {
		try {
			 Claims claims = Jwts.parser().setSigningKey(segredo).parseClaimsJws(token).getBody();
			 return Long.parseLong(claims.getSubject());
		} catch(Exception e) {
			return null;
		}
	}

}
