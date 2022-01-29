package com.alura.dtos.converters;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.alura.dtos.TopicoAtualizaDto;
import com.alura.dtos.TopicoCadastroDto;
import com.alura.dtos.TopicoDetalhadoDto;
import com.alura.dtos.TopicoDto;
import com.alura.model.domain.Topico;
import com.alura.repository.CursoRepository;

@Component
public class TopicoConverter {
	
	@Autowired
	private RespostaConverter respostaConverter;

	@Autowired
	private CursoRepository cursoRepository;

	public TopicoDto converterTopicoParaTopicoDto(Topico topico) {
		TopicoDto topicoDto = new TopicoDto();
		topicoDto.setId(topico.getId());
		topicoDto.setTitulo(topico.getTitulo());
		topicoDto.setMensagem(topico.getMensagem());
		topicoDto.setDataCriacao(topico.getDataCriacao());
		return topicoDto;
	}

	public Page<TopicoDto> converterTopicoParaTopicoDto(Page<Topico> topicos) {
		return topicos.map(topico -> converterTopicoParaTopicoDto(topico));
	}

	public Topico converterTopicoCadastroDtoParaTopico(TopicoCadastroDto topicoCadastroDto) {
		Topico topico = new Topico();
		topico.setTitulo(topicoCadastroDto.getTitulo());
		topico.setMensagem(topicoCadastroDto.getMensagem());
		topico.setCurso(cursoRepository.findByNome(topicoCadastroDto.getNomeCurso()));
		topico.setDataCriacao(LocalDateTime.now());
		return topico;
	}

	public TopicoDetalhadoDto converterTopicoParaTopicoDetalhadoDto(Topico topico) {
		TopicoDetalhadoDto topicoDetalhadoDto = new TopicoDetalhadoDto();
		topicoDetalhadoDto.setId(topico.getId());
		topicoDetalhadoDto.setTitulo(topico.getTitulo());
		topicoDetalhadoDto.setMensagem(topico.getMensagem());
		topicoDetalhadoDto.setNomeDoAutor(topico.getAutor().getNome());
		topicoDetalhadoDto.setStatus(topico.getStatus());
		topicoDetalhadoDto.setDataCriacao(topico.getDataCriacao());
		topicoDetalhadoDto.setRespostaListagemDtos(respostaConverter.converterRespostaParaRespostaDto(topico.getRespostas()));
		return topicoDetalhadoDto;
	}

	public Topico converterTopicoAtualizaDtoParaTopico(TopicoAtualizaDto topicoAtualizaDto, Topico topico) {
		topico.setTitulo(topicoAtualizaDto.getTitulo());
		topico.setMensagem(topicoAtualizaDto.getMensagem());
		return topico;
	}

}
