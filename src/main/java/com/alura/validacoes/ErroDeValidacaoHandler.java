package com.alura.validacoes;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	// Para não termos problemas com diferença de idioma. Altera o idioma da mensagem de acordo com o valor do header Accept-Language.
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handle(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		Stream<ErroDeCadastro> errosDeCadastro = fieldErrors.stream().map(errors -> new ErroDeCadastro(errors.getField(), messageSource.getMessage(errors, LocaleContextHolder.getLocale())));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errosDeCadastro);
	}

}
