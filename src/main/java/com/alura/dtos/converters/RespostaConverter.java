package com.alura.dtos.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alura.dtos.RespostaDto;
import com.alura.model.domain.Resposta;

@Component
public class RespostaConverter {

	public RespostaDto converterRespostaParaRespostaDto(Resposta resposta) {
		RespostaDto respostaDto = new RespostaDto();
		respostaDto.setId(resposta.getId());
		respostaDto.setMensagem(resposta.getMensagem());
		respostaDto.setNomeAutor(resposta.getAutor().getNome());
		respostaDto.setDataCriacao(resposta.getDataCriacao());
		return respostaDto;
	}

	public List<RespostaDto> converterRespostaParaRespostaDto(List<Resposta> respostas) {
		return respostas.stream().map(resposta -> converterRespostaParaRespostaDto(resposta)).collect(Collectors.toList());
	}

}
