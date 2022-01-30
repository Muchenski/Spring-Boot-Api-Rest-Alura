package com.alura.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alura.dtos.LoginDto;
import com.alura.dtos.TokenDto;
import com.alura.model.services.TokenService;

@RestController
@RequestMapping(value = "/auth")
public class AutenticacaoResource {

	@Autowired
	private AuthenticationManager authenticationManager; // Realiza autenticação via email e senha.
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginDto login) {
		try {	
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
		} catch(AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
