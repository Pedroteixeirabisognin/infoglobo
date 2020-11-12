package br.com.globo.Infoglobo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.globo.Infoglobo.model.Noticia;
import br.com.globo.Infoglobo.repository.NoticiaRepository;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class NoticiaRepositoryTest {

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Before
	public void setUp() {
		mongoTemplate.getDb().drop();
	}

	@Test
	public void testSave() {
		Noticia noticia = new Noticia("1", "Titulo", "Conteudo", "20/06/2020");

		noticia = noticiaRepository.save(noticia);

		Assertions.assertEquals(noticia, noticia);

		noticiaRepository.delete(noticia);
	}

	@Test
	public void testFindById() {

		Noticia noticia = new Noticia("1", "Titulo", "Conteudo", "20/06/2020");
		noticiaRepository.save(noticia);

		Optional<Noticia> noticiaFound = noticiaRepository.findById("1");

		Assertions.assertEquals("1", noticiaFound.get().getId());

		noticiaRepository.delete(noticia);

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

		noticiaRepository.saveAll(list);

		List<Noticia> noticiaList = noticiaRepository.findAll();

		Assertions.assertEquals(3, noticiaList.size());

		noticiaRepository.deleteAll(noticiaList);
	}
	
	
	@Test
	public void testDelete() {

		List<Noticia> list = new ArrayList<Noticia>();
		Noticia noticia1 = new Noticia("123", "Titulo 1", "Conteudo 1", "20/07/2020");
		Noticia noticia2 = new Noticia("1234", "Titulo 1", "Conteudo 1", "20/07/2020");
		Noticia noticia3 = new Noticia("12345", "Titulo 1", "Conteudo 1", "20/07/2020");

		list.add(noticia1);
		list.add(noticia2);
		list.add(noticia3);

		List<Noticia> noticiaList = noticiaRepository.saveAll(list);
		Assertions.assertEquals(3, noticiaList.size());
		noticiaRepository.deleteAll(noticiaList);
		
		noticiaList = noticiaRepository.findAll();

		
		Assertions.assertEquals(0, noticiaList.size());

		
	}
}
