package br.unifacisa.repository;

import br.unifacisa.domain.ListaDeDesejos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ListaDeDesejos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListaDeDesejosRepository extends MongoRepository<ListaDeDesejos, String> {

}
