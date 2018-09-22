package br.unifacisa.web.rest;

import br.unifacisa.JhipsterApp;

import br.unifacisa.domain.ListaDeDesejos;
import br.unifacisa.repository.ListaDeDesejosRepository;
import br.unifacisa.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static br.unifacisa.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ListaDeDesejosResource REST controller.
 *
 * @see ListaDeDesejosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class ListaDeDesejosResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RESTRICTED = false;
    private static final Boolean UPDATED_RESTRICTED = true;

    @Autowired
    private ListaDeDesejosRepository listaDeDesejosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restListaDeDesejosMockMvc;

    private ListaDeDesejos listaDeDesejos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListaDeDesejosResource listaDeDesejosResource = new ListaDeDesejosResource(listaDeDesejosRepository);
        this.restListaDeDesejosMockMvc = MockMvcBuilders.standaloneSetup(listaDeDesejosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListaDeDesejos createEntity() {
        ListaDeDesejos listaDeDesejos = new ListaDeDesejos()
            .title(DEFAULT_TITLE)
            .restricted(DEFAULT_RESTRICTED);
        return listaDeDesejos;
    }

    @Before
    public void initTest() {
        listaDeDesejosRepository.deleteAll();
        listaDeDesejos = createEntity();
    }

    @Test
    public void createListaDeDesejos() throws Exception {
        int databaseSizeBeforeCreate = listaDeDesejosRepository.findAll().size();

        // Create the ListaDeDesejos
        restListaDeDesejosMockMvc.perform(post("/api/lista-de-desejos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaDeDesejos)))
            .andExpect(status().isCreated());

        // Validate the ListaDeDesejos in the database
        List<ListaDeDesejos> listaDeDesejosList = listaDeDesejosRepository.findAll();
        assertThat(listaDeDesejosList).hasSize(databaseSizeBeforeCreate + 1);
        ListaDeDesejos testListaDeDesejos = listaDeDesejosList.get(listaDeDesejosList.size() - 1);
        assertThat(testListaDeDesejos.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testListaDeDesejos.isRestricted()).isEqualTo(DEFAULT_RESTRICTED);
    }

    @Test
    public void createListaDeDesejosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listaDeDesejosRepository.findAll().size();

        // Create the ListaDeDesejos with an existing ID
        listaDeDesejos.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restListaDeDesejosMockMvc.perform(post("/api/lista-de-desejos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaDeDesejos)))
            .andExpect(status().isBadRequest());

        // Validate the ListaDeDesejos in the database
        List<ListaDeDesejos> listaDeDesejosList = listaDeDesejosRepository.findAll();
        assertThat(listaDeDesejosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = listaDeDesejosRepository.findAll().size();
        // set the field null
        listaDeDesejos.setTitle(null);

        // Create the ListaDeDesejos, which fails.

        restListaDeDesejosMockMvc.perform(post("/api/lista-de-desejos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaDeDesejos)))
            .andExpect(status().isBadRequest());

        List<ListaDeDesejos> listaDeDesejosList = listaDeDesejosRepository.findAll();
        assertThat(listaDeDesejosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllListaDeDesejos() throws Exception {
        // Initialize the database
        listaDeDesejosRepository.save(listaDeDesejos);

        // Get all the listaDeDesejosList
        restListaDeDesejosMockMvc.perform(get("/api/lista-de-desejos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listaDeDesejos.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].restricted").value(hasItem(DEFAULT_RESTRICTED.booleanValue())));
    }
    
    @Test
    public void getListaDeDesejos() throws Exception {
        // Initialize the database
        listaDeDesejosRepository.save(listaDeDesejos);

        // Get the listaDeDesejos
        restListaDeDesejosMockMvc.perform(get("/api/lista-de-desejos/{id}", listaDeDesejos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listaDeDesejos.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.restricted").value(DEFAULT_RESTRICTED.booleanValue()));
    }

    @Test
    public void getNonExistingListaDeDesejos() throws Exception {
        // Get the listaDeDesejos
        restListaDeDesejosMockMvc.perform(get("/api/lista-de-desejos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateListaDeDesejos() throws Exception {
        // Initialize the database
        listaDeDesejosRepository.save(listaDeDesejos);

        int databaseSizeBeforeUpdate = listaDeDesejosRepository.findAll().size();

        // Update the listaDeDesejos
        ListaDeDesejos updatedListaDeDesejos = listaDeDesejosRepository.findById(listaDeDesejos.getId()).get();
        updatedListaDeDesejos
            .title(UPDATED_TITLE)
            .restricted(UPDATED_RESTRICTED);

        restListaDeDesejosMockMvc.perform(put("/api/lista-de-desejos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedListaDeDesejos)))
            .andExpect(status().isOk());

        // Validate the ListaDeDesejos in the database
        List<ListaDeDesejos> listaDeDesejosList = listaDeDesejosRepository.findAll();
        assertThat(listaDeDesejosList).hasSize(databaseSizeBeforeUpdate);
        ListaDeDesejos testListaDeDesejos = listaDeDesejosList.get(listaDeDesejosList.size() - 1);
        assertThat(testListaDeDesejos.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testListaDeDesejos.isRestricted()).isEqualTo(UPDATED_RESTRICTED);
    }

    @Test
    public void updateNonExistingListaDeDesejos() throws Exception {
        int databaseSizeBeforeUpdate = listaDeDesejosRepository.findAll().size();

        // Create the ListaDeDesejos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListaDeDesejosMockMvc.perform(put("/api/lista-de-desejos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listaDeDesejos)))
            .andExpect(status().isBadRequest());

        // Validate the ListaDeDesejos in the database
        List<ListaDeDesejos> listaDeDesejosList = listaDeDesejosRepository.findAll();
        assertThat(listaDeDesejosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteListaDeDesejos() throws Exception {
        // Initialize the database
        listaDeDesejosRepository.save(listaDeDesejos);

        int databaseSizeBeforeDelete = listaDeDesejosRepository.findAll().size();

        // Get the listaDeDesejos
        restListaDeDesejosMockMvc.perform(delete("/api/lista-de-desejos/{id}", listaDeDesejos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ListaDeDesejos> listaDeDesejosList = listaDeDesejosRepository.findAll();
        assertThat(listaDeDesejosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListaDeDesejos.class);
        ListaDeDesejos listaDeDesejos1 = new ListaDeDesejos();
        listaDeDesejos1.setId("id1");
        ListaDeDesejos listaDeDesejos2 = new ListaDeDesejos();
        listaDeDesejos2.setId(listaDeDesejos1.getId());
        assertThat(listaDeDesejos1).isEqualTo(listaDeDesejos2);
        listaDeDesejos2.setId("id2");
        assertThat(listaDeDesejos1).isNotEqualTo(listaDeDesejos2);
        listaDeDesejos1.setId(null);
        assertThat(listaDeDesejos1).isNotEqualTo(listaDeDesejos2);
    }
}
