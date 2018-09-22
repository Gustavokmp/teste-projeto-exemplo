package br.unifacisa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A ListaDeDesejos.
 */
@Document(collection = "lista_de_desejos")
public class ListaDeDesejos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("title")
    private String title;

    @Field("restricted")
    private Boolean restricted;

    @OneToMany(mappedBy = "listaDeDesejos")
    private Set<Produto> produtos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("listaDeDesejos")
    private Cliente cliente;

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

    public ListaDeDesejos title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isRestricted() {
        return restricted;
    }

    public ListaDeDesejos restricted(Boolean restricted) {
        this.restricted = restricted;
        return this;
    }

    public void setRestricted(Boolean restricted) {
        this.restricted = restricted;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public ListaDeDesejos produtos(Set<Produto> produtos) {
        this.produtos = produtos;
        return this;
    }

    public ListaDeDesejos addProduto(Produto produto) {
        this.produtos.add(produto);
        produto.setListaDeDesejos(this);
        return this;
    }

    public ListaDeDesejos removeProduto(Produto produto) {
        this.produtos.remove(produto);
        produto.setListaDeDesejos(null);
        return this;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ListaDeDesejos cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        ListaDeDesejos listaDeDesejos = (ListaDeDesejos) o;
        if (listaDeDesejos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), listaDeDesejos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ListaDeDesejos{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", restricted='" + isRestricted() + "'" +
            "}";
    }
}
