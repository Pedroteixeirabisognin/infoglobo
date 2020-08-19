package br.com.globo.Infoglobo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.globo.Infoglobo.dto.NoticiaDto;
import br.com.globo.Infoglobo.dto.NoticiaResponseDto;
import br.com.globo.Infoglobo.model.Noticia;
import br.com.globo.Infoglobo.service.NoticiaService;

@RestController
@RequestMapping(value = "/noticia")
public class NoticiaController {

	private NoticiaService noticiaService;

	@Autowired
	public NoticiaController(NoticiaService noticiaService) {
		this.noticiaService = noticiaService;
	}

	@PutMapping("/{id}")
	public ResponseEntity<NoticiaResponseDto> update(@PathVariable String id,
			@RequestBody @Valid NoticiaDto noticiaDto) {

		Optional<Noticia> noticiaOpt = noticiaService.findById(id);

		if (noticiaOpt.isPresent()) {

			NoticiaResponseDto noticiaResponseDto = NoticiaResponseDto.setTituloEConteudo(noticiaOpt.get(), noticiaDto);
			Noticia noticiaAtualizada = new Noticia (noticiaResponseDto.getId(),noticiaResponseDto.getTitulo(),noticiaResponseDto.getConteudo(),noticiaResponseDto.getDataPublicacao());
			noticiaAtualizada = noticiaService.save(noticiaAtualizada);
			noticiaResponseDto = NoticiaResponseDto.transformaEmDTO(noticiaAtualizada);
			Link link = WebMvcLinkBuilder.linkTo(NoticiaController.class).slash(noticiaOpt.get().getId()).withSelfRel();

			return new ResponseEntity<NoticiaResponseDto>(noticiaResponseDto.add(link), HttpStatus.OK);

		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {

		Optional<Noticia> noticia = noticiaService.findById(id);

		if (noticia.isPresent()) {
			noticiaService.deleteById(id);
			return ResponseEntity.noContent().build();

		}

		return ResponseEntity.notFound().build();

	}

	@GetMapping(value = "/")
	public ResponseEntity<List<NoticiaResponseDto>> findAll() {

		List<Noticia> noticias = noticiaService.findAll();
		List<NoticiaResponseDto> noticiaRespostaDto = new ArrayList<>();

		noticias.forEach(noticia -> {
			Link link = WebMvcLinkBuilder.linkTo(NoticiaController.class).slash(noticia.getId()).withSelfRel();

			noticiaRespostaDto.add(NoticiaResponseDto.transformaEmDTO(noticia).add(link));
		});
		return ResponseEntity.ok().body(noticiaRespostaDto);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<NoticiaResponseDto> findById(@PathVariable("id") String id) {

		Optional<Noticia> noticia = noticiaService.findById(id);
		if (noticia.isPresent()) {
			Link link = WebMvcLinkBuilder.linkTo(NoticiaController.class).slash(noticia.get().getId()).withSelfRel();

			return ResponseEntity.ok(NoticiaResponseDto.transformaEmDTO(noticia.get()).add(link));
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<NoticiaResponseDto> save(@RequestBody @Valid NoticiaDto noticiaDto) {

		Noticia noticia = noticiaService.save(noticiaDto.transformaParaNoticiaSemData());
		Link link = WebMvcLinkBuilder.linkTo(NoticiaController.class).slash(noticia.getId()).withSelfRel();
		return new ResponseEntity<NoticiaResponseDto>(NoticiaResponseDto.transformaEmDTO(noticia).add(link),
				HttpStatus.CREATED);

	}

}
