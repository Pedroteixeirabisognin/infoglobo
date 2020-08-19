package br.com.globo.Infoglobo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.globo.Infoglobo.model.Noticia;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class NoticiaDto {

	@NotNull
	@NotEmpty
	private String titulo;

	@NotNull
	@NotEmpty
	private String conteudo;

	private String dataPublicacao;

	public Noticia transformaParaNoticiaSemData() {

		return new Noticia(titulo, conteudo);
	}

	public NoticiaDto(@NotNull @NotEmpty String titulo, @NotNull @NotEmpty String conteudo) {
		this.titulo = titulo;
		this.conteudo = conteudo;
	}

}
