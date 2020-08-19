package br.com.globo.Infoglobo;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.globo.Infoglobo.dto.NoticiaDto;
import br.com.globo.Infoglobo.dto.NoticiaResponseDto;
import br.com.globo.Infoglobo.model.Noticia;
import br.com.globo.Infoglobo.repository.NoticiaRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoticiaIntegrationTest {
	protected final String BASE_PATH = "http://localhost:";

	@Autowired
	private TestRestTemplate restTeamplate;

	@Autowired
	private NoticiaRepository noticiaRepository;

	@LocalServerPort
	private int port;

	@Test
	public void updateAndReturn200() {

		Noticia noticia = new Noticia("TITULO", "CONTEUDO");

		String id = noticiaRepository.save(noticia).getId();

		NoticiaDto noticiaDto = new NoticiaDto("TITULO ATUALIZADO", "CONTEUDO ATUALIZADO");

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(noticiaDto, createHeaders());
		ResponseEntity<NoticiaDto> response = restTeamplate.exchange("/noticia/{id}", HttpMethod.PUT, entity,
				NoticiaDto.class, id);

		Assert.assertEquals(200, response.getStatusCodeValue());
		Assert.assertEquals("TITULO ATUALIZADO", response.getBody().getTitulo());
		Assert.assertEquals("CONTEUDO ATUALIZADO", response.getBody().getConteudo());

		noticiaRepository.deleteById(id);

	}

	@Test
	public void updateWithTituloEmptyAndReturn400() {

		Noticia noticia = new Noticia("TITULO", "CONTEUDO");

		String id = noticiaRepository.save(noticia).getId();

		NoticiaDto noticiaDto = new NoticiaDto("", "CONTEUDO ATUALIZADO");

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(noticiaDto, createHeaders());
		ResponseEntity<String> response = restTeamplate.exchange("/noticia/{id}", HttpMethod.PUT, entity,
				String.class, id);

		String jsonReturn = "[{\"campo\":\"titulo\",\"erro\":\"não deve estar vazio\"}]";

		Assert.assertEquals(400, response.getStatusCodeValue());
		Assert.assertEquals(new ResponseEntity<String>(jsonReturn, HttpStatus.BAD_REQUEST).getBody(),
				response.getBody());

		noticiaRepository.deleteById(id);

	}

	@Test
	public void updateWithConteudoEmptyAndReturn400() {

		Noticia noticia = new Noticia("TITULO", "CONTEUDO");

		String id = noticiaRepository.save(noticia).getId();

		NoticiaDto noticiaDto = new NoticiaDto("TITULO ATUALIZADO", "");

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(noticiaDto, createHeaders());
		ResponseEntity<String> response = restTeamplate.exchange("/noticia/{id}", HttpMethod.PUT, entity,
				String.class, id);

		String jsonReturn = "[{\"campo\":\"conteudo\",\"erro\":\"não deve estar vazio\"}]";

		Assert.assertEquals(400, response.getStatusCodeValue());
		Assert.assertEquals(new ResponseEntity<String>(jsonReturn, HttpStatus.BAD_REQUEST).getBody(),
				response.getBody());

		noticiaRepository.deleteById(id);

	}

	@Test
	public void updateAndReturn404() {

		NoticiaDto noticiaDto = new NoticiaDto("TITULO ATUALIZADO", "CONTEUDO ATUALIZADO");

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(noticiaDto, createHeaders());
		ResponseEntity<NoticiaDto> response = restTeamplate.exchange("/noticia/{id}", HttpMethod.PUT, entity,
				NoticiaDto.class, "123");

		Assert.assertEquals(404, response.getStatusCodeValue());

	}

	@Test
	public void deleteWhenNoticiaExistsAndReturns204() {

		Noticia noticia = new Noticia("TITULO", "CONTEUDO");

		String id = noticiaRepository.save(noticia).getId();
		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(createHeaders());
		ResponseEntity<String> response = restTeamplate.exchange("/noticia/" + id, HttpMethod.DELETE, entity,
				String.class);
		Assert.assertEquals(204, response.getStatusCodeValue());

		noticiaRepository.deleteById(id);

	}

	@Test
	public void deleteWhenNoticiaNotExistsAndReturns404() {
		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(createHeaders());

		ResponseEntity<String> response = restTeamplate.exchange("/noticia/{id}", HttpMethod.DELETE, entity,
				String.class, "-1");
		Assert.assertEquals(404, response.getStatusCodeValue());

	}

	@Test
	public void findAllAndReturn200() {

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(createHeaders());
		ResponseEntity<String> response = restTeamplate.exchange("/noticia/", HttpMethod.GET, entity, String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void findByIdWhenIdExistsAndReturn200() {
		Noticia noticia = new Noticia("TITULO", "CONTEUDO");

		String id = noticiaRepository.save(noticia).getId();

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(createHeaders());

		ResponseEntity<String> response = restTeamplate.exchange("/noticia/{id}", HttpMethod.GET, entity, String.class,
				id);

		Assert.assertEquals(200, response.getStatusCodeValue());
		noticiaRepository.deleteById(id);

	}

	@Test
	public void findByIdWhenIdNotExistsAndReturn404() {

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(createHeaders());

		ResponseEntity<NoticiaResponseDto> response = restTeamplate.exchange("/noticia/{id}", HttpMethod.GET, entity,
				NoticiaResponseDto.class, "-1");
		Assert.assertEquals(404, response.getStatusCodeValue());

	}

	@Test
	public void saveNoticiaAndReturns201() {
		NoticiaDto noticia = new NoticiaDto("TITULO", "CONTEUDO");
		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(noticia, createHeaders());
		ResponseEntity<NoticiaResponseDto> response = restTeamplate.exchange("/noticia/", HttpMethod.POST, entity,
				NoticiaResponseDto.class);

		Assert.assertEquals(201, response.getStatusCodeValue());

	}

	@Test
	public void saveWhenConteudoIsEmptyAndReturns400() {

		NoticiaDto noticiaDto = new NoticiaDto("Titulo", "");
		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(noticiaDto, createHeaders());
		ResponseEntity<String> response = restTeamplate.exchange("/noticia/", HttpMethod.POST, entity,
				String.class);

		String jsonReturn = "[{\"campo\":\"conteudo\",\"erro\":\"não deve estar vazio\"}]";

		Assert.assertEquals(400, response.getStatusCodeValue());
		Assert.assertEquals(new ResponseEntity<String>(jsonReturn, HttpStatus.BAD_REQUEST).getBody(),
				response.getBody());

	}

	@Test
	public void saveWhenTituloIsEmptyAndReturns400() throws JSONException {
		NoticiaDto noticiaDto = new NoticiaDto("", "Conteudo");

		HttpEntity<NoticiaDto> entity = new HttpEntity<NoticiaDto>(noticiaDto, createHeaders());
		ResponseEntity<String> response = restTeamplate.exchange("/noticia/", HttpMethod.POST, entity,
				String.class);

		String jsonReturn = "[{\"campo\":\"titulo\",\"erro\":\"não deve estar vazio\"}]";

		Assert.assertEquals(400, response.getStatusCodeValue());
		Assert.assertEquals(new ResponseEntity<String>(jsonReturn, HttpStatus.BAD_REQUEST).getBody(),
				response.getBody());

	}

	public HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("infoglobo", "12345");
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
}
