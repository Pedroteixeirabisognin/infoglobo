package br.com.globo.Infoglobo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.globo.Infoglobo.model.Noticia;
import br.com.globo.Infoglobo.repository.NoticiaRepository;

@Service
public class NoticiaService {

	private NoticiaRepository noticiaRepository;

	@Autowired
	public NoticiaService(NoticiaRepository noticiaRepository) {
		this.noticiaRepository = noticiaRepository;
	}
	
	
	@CacheEvict(cacheNames = "noticias", allEntries = true)
	public void deleteById(String id) {
		noticiaRepository.deleteById(id);
	}


	@Cacheable(cacheNames = "noticias")
	public List<Noticia> findAll() {
		return noticiaRepository.findAll();
	}
	
	@Cacheable(cacheNames = "noticias")
	public Optional<Noticia> findById(String id) {

		Optional<Noticia> noticia = noticiaRepository.findById(id);
		return noticia;
	}
	

	@CacheEvict(cacheNames = "noticias", allEntries = true)
	public Noticia save(Noticia noticia) {
		return noticiaRepository.save(noticia);
	}






}
