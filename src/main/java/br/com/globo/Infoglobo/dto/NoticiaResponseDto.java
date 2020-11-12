package br.com.globo.Infoglobo.dto;

import org.springframework.hateoas.RepresentationModel;

import br.com.globo.Infoglobo.model.Noticia;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NoticiaResponseDto extends RepresentationModel<NoticiaResponseDto> {

	private String id;

	private String titulo;

	private String conteudo;

	private String dataPublicacao;

	public static NoticiaResponseDto transformaEmDTO(Noticia noticia) {
		return new NoticiaResponseDto(noticia.getId(), noticia.getTitulo(), noticia.getConteudo(),
				noticia.getDataPublicacao());
	}

	public static NoticiaResponseDto setTituloEConteudo(Noticia noticia, NoticiaDto noticiaDto) {
		return new NoticiaResponseDto(noticia.getId(),
				noticiaDto.getTitulo() != null ? noticiaDto.getTitulo() : noticia.getTitulo(),
				noticiaDto.getConteudo() != null ? noticiaDto.getConteudo() : noticia.getConteudo(),
				noticia.getDataPublicacao());
	}


}
