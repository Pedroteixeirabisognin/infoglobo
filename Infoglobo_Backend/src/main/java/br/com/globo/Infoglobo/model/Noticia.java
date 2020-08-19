package br.com.globo.Infoglobo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.globo.Infoglobo.util.FormataData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "noticia")
public class Noticia {

	@Id
	private String id;
	@NotEmpty
	@NotNull
	private String titulo;
	@NotEmpty
	@NotNull
	private String conteudo;

	private String dataPublicacao;

	public Noticia(String titulo, String conteudo, String dataPublicacao) {

		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataPublicacao = dataPublicacao == null? FormataData.retornaDataFormatadaLocal() : dataPublicacao;
	}
	
	public Noticia(String id, String titulo, String conteudo, String dataPublicacao) {

		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataPublicacao = dataPublicacao == null? FormataData.retornaDataFormatadaLocal() : dataPublicacao;
	}

	public Noticia(String titulo, String conteudo) {

		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataPublicacao = FormataData.retornaDataFormatadaLocal();

	}
	

	
	
}
