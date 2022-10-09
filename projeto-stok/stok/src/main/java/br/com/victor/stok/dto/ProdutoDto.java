package br.com.victor.stok.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.victor.stok.model.Categoria;
import br.com.victor.stok.model.Produto;

public class ProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private LocalDate dataCadastro;
	private String fornecedor;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.dataCadastro = produto.getDataCadastro();
		this.fornecedor = produto.getFornecedor();
		this.categoria = produto.getCategoria();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNomeString(String nomeString) {
		this.nome = nomeString;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public static List<ProdutoDto> converter(List<Produto> produto) {
		return produto.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
}
