package br.unifacisa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.unifacisa.domain.enumeration.StatusDaCategoria;

/**
 * A Categoria.
 */
@Document(collection = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("descricao")
    private String descricao;

    @Field("classificacao")
    private Integer classificacao;

    @Field("status")
    private StatusDaCategoria status;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Categoria parent;

    @ManyToMany
    @JoinTable(name = "categoria_produto",
               joinColumns = @JoinColumn(name = "categorias_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "produtos_id", referencedColumnName = "id"))
    private Set<Produto> produtos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public Categoria classificacao(Integer classificacao) {
        this.classificacao = classificacao;
        return this;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    public StatusDaCategoria getStatus() {
        return status;
    }

    public Categoria status(StatusDaCategoria status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusDaCategoria status) {
        this.status = status;
    }

    public Categoria getParent() {
        return parent;
    }

    public Categoria parent(Categoria categoria) {
        this.parent = categoria;
        return this;
    }

    public void setParent(Categoria categoria) {
        this.parent = categoria;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public Categoria produtos(Set<Produto> produtos) {
        this.produtos = produtos;
        return this;
    }

    public Categoria addProduto(Produto produto) {
        this.produtos.add(produto);
        produto.getCategorias().add(this);
        return this;
    }

    public Categoria removeProduto(Produto produto) {
        this.produtos.remove(produto);
        produto.getCategorias().remove(this);
        return this;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
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
        Categoria categoria = (Categoria) o;
        if (categoria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Categoria{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", classificacao=" + getClassificacao() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
