package br.unifacisa.repository;

import br.unifacisa.domain.Categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Categoria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {

    @Query("select distinct categoria from Categoria categoria left join fetch categoria.produtos"
    		+"select count(distinct categoria) from Categoria categoria")
    Page<Categoria> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct categoria from Categoria categoria left join fetch categoria.produtos")
    List<Categoria> findAllWithEagerRelationships();

    @Query("select categoria from Categoria categoria left join fetch categoria.produtos where categoria.id =:id")
    Optional<Categoria> findOneWithEagerRelationships(@Param("id") Long id);

}
