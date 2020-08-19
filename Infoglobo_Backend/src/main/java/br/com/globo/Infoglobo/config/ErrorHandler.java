package br.com.globo.Infoglobo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.globo.Infoglobo.dto.error.ErrorDataAcessDto;
import br.com.globo.Infoglobo.dto.error.ErrorMethodDto;
import br.com.globo.Infoglobo.dto.error.ErrorValidationDto;

@RestControllerAdvice
public class ErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorValidationDto> handle(MethodArgumentNotValidException exception) {
		List<ErrorValidationDto> errorValidationDto = new ArrayList<ErrorValidationDto>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(error -> {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			ErrorValidationDto erroNovo = new ErrorValidationDto(error.getField(), mensagem);
			errorValidationDto.add(erroNovo);
		});
		return errorValidationDto;
	}

	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorMethodDto handleMethodNotSuported(HttpRequestMethodNotSupportedException exception) {
		ArrayList<String> suported = new ArrayList<String>(Arrays.asList(exception.getSupportedMethods()));
		ErrorMethodDto erroMethodDto = new ErrorMethodDto(exception.getMethod(), exception.getMessage(), suported);
		return erroMethodDto;
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ErrorDataAcessDto handleDataAccessResourceFailureException(DataAccessResourceFailureException exception) {
		ErrorDataAcessDto errorDataAcessDto = new ErrorDataAcessDto(exception.getLocalizedMessage());
		return errorDataAcessDto;
	}

}
