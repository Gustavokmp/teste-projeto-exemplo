package br.unifacisa.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.unifacisa.domain.Categoria;
import br.unifacisa.repository.CategoriaRepository;

/**
 * Service Implementation for managing Categoria.
 */
@Service
public class CategoriaService {

    private final Logger log = LoggerFactory.getLogger(CategoriaService.class);

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Save a categoria.
     *
     * @param categoria the entity to save
     * @return the persisted entity
     */
    public Categoria save(Categoria categoria) {
        log.debug("Request to save Categoria : {}", categoria);        return categoriaRepository.save(categoria);
    }

    /**
     * Get all the categorias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Categoria> findAll(Pageable pageable) {
        log.debug("Request to get all Categorias");
        return categoriaRepository.findAll(pageable);
    }

    /**
     * Get all the Categoria with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Categoria> findAllWithEagerRelationships(Pageable pageable) {
        return categoriaRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one categoria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<Categoria> findOne(String id) {
        log.debug("Request to get Categoria : {}", id);
        return categoriaRepository.findOneWithEagerRelationships(new Long(id));
    }

    /**
     * Delete the categoria by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Categoria : {}", id);
        categoriaRepository.deleteById(id);
    }
}
