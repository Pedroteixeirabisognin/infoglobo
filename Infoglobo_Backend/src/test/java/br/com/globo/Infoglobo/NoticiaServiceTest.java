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

import br.com.globo.Infoglobo.model.Noticia;
import br.com.globo.Infoglobo.repository.NoticiaRepository;
import br.com.globo.Infoglobo.service.NoticiaService;

@RunWith(SpringRunner.class)
public class NoticiaServiceTest {

	@Mock
	private NoticiaRepository noticiaRepository;

	private NoticiaService noticiaService;

	@Before
	public void setUp() {
		noticiaRepository = Mockito.mock(NoticiaRepository.class);
		noticiaService = new NoticiaService(noticiaRepository);

	}

	@Test
	public void getAllNoticiasTest() {

		List<Noticia> list = new ArrayList<Noticia>();
		Noticia noticia1 = new Noticia("123", "Titulo 1", "Conteudo 1", "20/07/2020");
		Noticia noticia2 = new Noticia("1234", "Titulo 1", "Conteudo 1", "20/07/2020");
		Noticia noticia3 = new Noticia("12345", "Titulo 1", "Conteudo 1", "20/07/2020");

		list.add(noticia1);
		list.add(noticia2);
		list.add(noticia3);

		Mockito.when(noticiaRepository.findAll()).thenReturn(list);

		List<Noticia> noticiaList = noticiaService.findAll();

		Assertions.assertEquals(3, noticiaList.size());
		Mockito.verify(noticiaRepository, Mockito.times(1)).findAll();
	}

	@Test
	public void getNoticiaByIdTest() {
		Optional<Noticia> noticia = Optional.of(new Noticia("1", "Titulo", "Conteudo", "20/06/2020"));
		Mockito.when(noticiaRepository.findById("1")).thenReturn(noticia);

		Optional<Noticia> noticiaReturn = noticiaService.findById("1");

		Assertions.assertEquals("Titulo", noticiaReturn.get().getTitulo());
		Assertions.assertEquals("Conteudo", noticiaReturn.get().getConteudo());
		Assertions.assertEquals("20/06/2020", noticiaReturn.get().getDataPublicacao());
	}

	@Test
	public void saveNoticiaTest() {
		Noticia noticia = new Noticia("1", "Titulo", "Conteudo", "20/06/2020");

		noticiaService.save(noticia);

		Mockito.verify(noticiaRepository, Mockito.times(1)).save(noticia);
	}

	@Test
	public void deleteByIdTest() {

		noticiaService.deleteById("1");

		Mockito.verify(noticiaRepository, Mockito.times(1)).deleteById("1");
	}
}
