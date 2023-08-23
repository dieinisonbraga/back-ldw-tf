package com.example.api.model.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto", schema = "eletronicos")
public class Produto {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "quantidade" , nullable = false)
	private Integer quantidade;
	
	@Column(name = "valor", nullable = false)
	private Float valor;
	
	@Column(name = "preco_unitario")
	private Float preco_unitario;
	
	@Column(name = "image_path")
	private String image_path;
	
	@ManyToOne // Many é da minha entidade atual, one é para entidade que mapeie
	@JoinColumn(name = "id_usuario")
	private Usuario usuario; //só será salvo o id do usuário

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Float getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(Float preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, image_path, nome, preco_unitario, quantidade, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id) && Objects.equals(image_path, other.image_path)
				&& Objects.equals(nome, other.nome) && Objects.equals(preco_unitario, other.preco_unitario)
				&& Objects.equals(quantidade, other.quantidade) && Objects.equals(valor, other.valor);
	}
	
}
