package com.alura.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alura.filters.AutenticacaoViaTokenFilter;
import com.alura.model.services.AutenticacaoService;
import com.alura.model.services.TokenService;
import com.alura.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String[] PUBLIC_GET = { "/topicos", "/topicos/**", "/actuator/**" };
	private final String[] PUBLIC_POST = { "/auth/**" };

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// Método responsável para configurar autenticação.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(passwordEncoder());
	}

	// Método responsável para configurar autorizações.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_GET).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_POST).permitAll()
			.anyRequest().authenticated()
			.and()
			// Removido para não criar sessão .formLogin(); // Para o Spring gerar um formulário de autenticação.
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Para não criar sessão.
			.and()
			.addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); // Registrando o nosso filtro de autenticação.
	}

	// Método responsável para configurar acesso a recursos estáticos (html, css, js, imagens...).
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
