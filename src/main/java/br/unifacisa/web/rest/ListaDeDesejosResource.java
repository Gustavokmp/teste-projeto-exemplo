package br.unifacisa.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.unifacisa.domain.ListaDeDesejos;
import br.unifacisa.repository.ListaDeDesejosRepository;
import br.unifacisa.web.rest.errors.BadRequestAlertException;
import br.unifacisa.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ListaDeDesejos.
 */
@RestController
@RequestMapping("/api")
public class ListaDeDesejosResource {

    private final Logger log = LoggerFactory.getLogger(ListaDeDesejosResource.class);

    private static final String ENTITY_NAME = "listaDeDesejos";

    private final ListaDeDesejosRepository listaDeDesejosRepository;

    public ListaDeDesejosResource(ListaDeDesejosRepository listaDeDesejosRepository) {
        this.listaDeDesejosRepository = listaDeDesejosRepository;
    }

    /**
     * POST  /lista-de-desejos : Create a new listaDeDesejos.
     *
     * @param listaDeDesejos the listaDeDesejos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new listaDeDesejos, or with status 400 (Bad Request) if the listaDeDesejos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lista-de-desejos")
    @Timed
    public ResponseEntity<ListaDeDesejos> createListaDeDesejos(@Valid @RequestBody ListaDeDesejos listaDeDesejos) throws URISyntaxException {
        log.debug("REST request to save ListaDeDesejos : {}", listaDeDesejos);
        if (listaDeDesejos.getId() != null) {
            throw new BadRequestAlertException("A new listaDeDesejos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListaDeDesejos result = listaDeDesejosRepository.save(listaDeDesejos);
        return ResponseEntity.created(new URI("/api/lista-de-desejos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lista-de-desejos : Updates an existing listaDeDesejos.
     *
     * @param listaDeDesejos the listaDeDesejos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated listaDeDesejos,
     * or with status 400 (Bad Request) if the listaDeDesejos is not valid,
     * or with status 500 (Internal Server Error) if the listaDeDesejos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lista-de-desejos")
    @Timed
    public ResponseEntity<ListaDeDesejos> updateListaDeDesejos(@Valid @RequestBody ListaDeDesejos listaDeDesejos) throws URISyntaxException {
        log.debug("REST request to update ListaDeDesejos : {}", listaDeDesejos);
        if (listaDeDesejos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListaDeDesejos result = listaDeDesejosRepository.save(listaDeDesejos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, listaDeDesejos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lista-de-desejos : get all the listaDeDesejos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of listaDeDesejos in body
     */
    @GetMapping("/lista-de-desejos")
    @Timed
    public List<ListaDeDesejos> getAllListaDeDesejos() {
        log.debug("REST request to get all ListaDeDesejos");
        return listaDeDesejosRepository.findAll();
    }

    /**
     * GET  /lista-de-desejos/:id : get the "id" listaDeDesejos.
     *
     * @param id the id of the listaDeDesejos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the listaDeDesejos, or with status 404 (Not Found)
     */
    @GetMapping("/lista-de-desejos/{id}")
    @Timed
    public ResponseEntity<ListaDeDesejos> getListaDeDesejos(@PathVariable String id) {
        log.debug("REST request to get ListaDeDesejos : {}", id);
        Optional<ListaDeDesejos> listaDeDesejos = listaDeDesejosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(listaDeDesejos);
    }

    /**
     * DELETE  /lista-de-desejos/:id : delete the "id" listaDeDesejos.
     *
     * @param id the id of the listaDeDesejos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lista-de-desejos/{id}")
    @Timed
    public ResponseEntity<Void> deleteListaDeDesejos(@PathVariable String id) {
        log.debug("REST request to delete ListaDeDesejos : {}", id);

        listaDeDesejosRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
