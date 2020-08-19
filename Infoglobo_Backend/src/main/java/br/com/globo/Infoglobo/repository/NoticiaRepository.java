package br.com.globo.Infoglobo.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.globo.Infoglobo.model.Noticia;


@Repository
public interface NoticiaRepository extends MongoRepository<Noticia, String> {

}
