package com.alura.resources;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alura.dtos.TopicoAtualizaDto;
import com.alura.dtos.TopicoCadastroDto;
import com.alura.dtos.TopicoDetalhadoDto;
import com.alura.dtos.TopicoDto;
import com.alura.dtos.converters.TopicoConverter;
import com.alura.model.domain.Topico;
import com.alura.repository.TopicoRepository;
import com.alura.resources.utils.ResourceUtils;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoResource {

	@Autowired
	private ResourceUtils resourceUtils;

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private TopicoConverter topicoConverter;
	
	// Por padrão, o Spring ordena a paginação pela chave primária.
	// Podemos definir mais de um atributo para ser critério de ordenação, com direções diferentes.
	// http://localhost:8080/topicos?page=0&size=2&sort=titulo,asc&sort=dataCriacao,desc&nome=Spring+Boot
	
	// O Cache é inteligente e percebe alterações de valores nos parâmetros da requisição.
	@Cacheable(value = "filtrarPorNome")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<TopicoDto>> filtrarPorNome(
			@RequestParam(required = false) String nome, 
			// @RequestParam int pagina, 
			// @RequestParam int tamanho, 
			// @RequestParam String ordenarPor
			@PageableDefault(direction = Direction.DESC, sort = {"id", "dataCriacao"}, page = 0, size = 5) Pageable pageable
	) {
		
		// Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(Direction.ASC, ordenarPor));
		
		if (nome == null) {
			Page<Topico> topicos = topicoRepository.findAll(pageable);
			Page<TopicoDto> dtos = topicoConverter.converterTopicoParaTopicoDto(topicos);
			return resourceUtils.retornarLista(dtos);
		}
		Page<Topico> topicos = topicoRepository.findByCurso_NomeContainingIgnoreCase(nome, pageable);
		Page<TopicoDto> dtos = topicoConverter.converterTopicoParaTopicoDto(topicos);
		return resourceUtils.retornarLista(dtos);
	}

	@CacheEvict(value = "filtrarPorNome", allEntries = true)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoCadastroDto topicoCadastroDto) {
		Topico topico = topicoConverter.converterTopicoCadastroDtoParaTopico(topicoCadastroDto);
		topico = topicoRepository.save(topico);
		URI uri = resourceUtils.obterUriParaOId(topico.getId());
		return ResponseEntity.created(uri).body(topicoConverter.converterTopicoParaTopicoDto(topico));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<TopicoDetalhadoDto> obterPorId(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(topicoConverter.converterTopicoParaTopicoDetalhadoDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@CacheEvict(value = "filtrarPorNome", allEntries = true)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoAtualizaDto topicoAtualizaDto) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			Topico topico = topicoConverter.converterTopicoAtualizaDtoParaTopico(topicoAtualizaDto, optional.get());
			topico = topicoRepository.save(topico);
			return ResponseEntity.ok(topicoConverter.converterTopicoParaTopicoDto(topico));
		}
		return ResponseEntity.notFound().build();
	}

	@CacheEvict(value = "filtrarPorNome", allEntries = true)
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if (optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
