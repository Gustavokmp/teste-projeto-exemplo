package br.unifacisa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Produto.
 */
@Document(collection = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 50)
    @Field("title")
    private String title;

    @Field("palavra_chave")
    private String palavraChave;

    @Size(max = 50)
    @Field("descricao")
    private String descricao;

    @NotNull
    @Field("avaliacao")
    private Integer avaliacao;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private ListaDeDesejos listaDeDesejos;

    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    private Set<Categoria> categorias = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Produto title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public Produto palavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
        return this;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public Produto avaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
        return this;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public ListaDeDesejos getListaDeDesejos() {
        return listaDeDesejos;
    }

    public Produto listaDeDesejos(ListaDeDesejos listaDeDesejos) {
        this.listaDeDesejos = listaDeDesejos;
        return this;
    }

    public void setListaDeDesejos(ListaDeDesejos listaDeDesejos) {
        this.listaDeDesejos = listaDeDesejos;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public Produto categorias(Set<Categoria> categorias) {
        this.categorias = categorias;
        return this;
    }

    public Produto addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
        categoria.getProdutos().add(this);
        return this;
    }

    public Produto removeCategoria(Categoria categoria) {
        this.categorias.remove(categoria);
        categoria.getProdutos().remove(this);
        return this;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Produto produto = (Produto) o;
        if (produto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Produto{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", palavraChave='" + getPalavraChave() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", avaliacao=" + getAvaliacao() +
            "}";
    }
}
