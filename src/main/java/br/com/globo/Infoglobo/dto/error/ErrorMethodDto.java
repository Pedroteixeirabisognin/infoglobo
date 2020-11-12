package br.com.globo.Infoglobo.dto.error;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMethodDto {
	

	private String erro;
	private String metodo;
	private ArrayList<String> metodosSuportadosNesteFormato;

}
