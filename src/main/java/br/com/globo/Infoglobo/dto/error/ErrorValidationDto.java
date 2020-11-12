package br.com.globo.Infoglobo.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorValidationDto {

	private String campo;
	private String erro;
	
	public ErrorValidationDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
}
