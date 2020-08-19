package br.com.globo.Infoglobo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.globo.Infoglobo.controller.NoticiaController;
import br.com.globo.Infoglobo.dto.NoticiaDto;
import br.com.globo.Infoglobo.dto.NoticiaResponseDto;
import br.com.globo.Infoglobo.model.Noticia;
import br.com.globo.Infoglobo.service.NoticiaService;

@RunWith(SpringRunner.class)
public class NoticiaControllerTest {

	@Mock
	private NoticiaService noticiaService;

	private NoticiaController noticiaController;

	@Before
	public void setUp() {
		noticiaService = Mockito.mock(NoticiaService.class);
		noticiaController = new NoticiaController(noticiaService);

	}

	@Test
	public void testFindAll() {

		List<Noticia> list = new ArrayList<Noticia>();
		Noticia noticia1 = new Noticia("123", "Titulo 1", "Conteudo 1", "20/07/2020");
		Noticia noticia2 = new Noticia("1234", "Titulo 1", "Conteudo 1", "20/07/2020");
		Noticia noticia3 = new Noticia("12345", "Titulo 1", "Conteudo 1", "20/07/2020");

		list.add(noticia1);
		list.add(noticia2);
		list.add(noticia3);

		Mockito.when(noticiaService.findAll()).thenReturn(list);

		List<NoticiaResponseDto> noticiaList = noticiaController.findAll().getBody();

		Assertions.assertEquals(3, noticiaList.size());
		Mockito.verify(noticiaService, Mockito.times(1)).findAll();
	}

	@Test
	public void testUpdate() {

		NoticiaDto noticiaDto = new NoticiaDto("Titulo Atualizado", "Conteudo Atualizado");
		Optional<Noticia> noticia = Optional.of(new Noticia("123", "Titulo", "Conteudo", "20/06/2020"));

		Mockito.when(noticiaService.findById("123")).thenReturn(noticia);

		Noticia noticiaAtualizada = new Noticia("123", "Titulo Atualizado", "Conteudo Atualizado", "20/06/2020");
		Mockito.when(noticiaService.save(noticiaAtualizada)).thenReturn(noticiaAtualizada);

		noticiaController.update("123", noticiaDto);

		Mockito.verify(noticiaService, Mockito.times(1)).save(noticiaAtualizada);
		Mockito.verify(noticiaService, Mockito.times(1)).findById("123");

	}
	
	@Test
	public void testDelete() {
		
		Optional<Noticia> noticia = Optional.of(new Noticia("123", "Titulo", "Conteudo", "20/06/2020"));

		Mockito.when(noticiaService.findById("123")).thenReturn(noticia);
		Mockito.doNothing().when(noticiaService).deleteById("123");
		noticiaController.delete("123");
		
		Mockito.verify(noticiaService, Mockito.times(1)).findById("123");
		Mockito.verify(noticiaService, Mockito.times(1)).deleteById("123");
		
	}
	
	@Test
	public void testFindById() {
		
		Optional<Noticia> noticia = Optional.of(new Noticia("123", "Titulo", "Conteudo", "20/06/2020"));
		Mockito.when(noticiaService.findById("123")).thenReturn(noticia);

		noticiaController.findById("123");
		
		Mockito.verify(noticiaService, Mockito.times(1)).findById("123");

	}
	
	
	@Test
	public void testSave() {

		NoticiaDto noticiaDto = new NoticiaDto("Titulo Atualizado", "Conteudo Atualizado");
		Optional<Noticia> noticia = Optional.of(new Noticia("123", "Titulo", "Conteudo", "20/06/2020"));

		Mockito.when(noticiaService.findById("123")).thenReturn(noticia);

		Noticia noticiaAtualizada = new Noticia("123", "Titulo Atualizado", "Conteudo Atualizado", "20/06/2020");
		Mockito.when(noticiaService.save(noticiaAtualizada)).thenReturn(noticiaAtualizada);

		noticiaController.update("123", noticiaDto);

		Mockito.verify(noticiaService, Mockito.times(1)).save(noticiaAtualizada);
		Mockito.verify(noticiaService, Mockito.times(1)).findById("123");

	}
	
}
