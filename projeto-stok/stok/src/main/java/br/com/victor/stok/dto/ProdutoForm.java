package br.com.victor.stok.dto;
import br.com.victor.stok.model.Categoria;
import br.com.victor.stok.model.Produto;
import br.com.victor.stok.repository.ProdutosRepository;

public class ProdutoForm {
	
	private String nome;
	private String descricao;
	private String fornecedor;
	private Integer categoriaInt;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Integer getCategoria() {
		return categoriaInt;
	}
	public void setCategoria(Integer categoria) {
		this.categoriaInt = categoria;
	}
	public Produto converter() {
		Categoria categoria = null;
		switch (categoriaInt) {
			case 1:
				categoria = Categoria.ELETRONICOS;
				break;
			case 2:
				categoria = Categoria.ELETRODOMESTICOS;
				break;
			case 3:
				categoria = Categoria.MOVEIS;
				break;
			default:
				break;
		}
		
		return new Produto(nome, descricao, fornecedor, categoria);
	}
	
	public Produto atualizar(Long id, ProdutosRepository produtosRepository) {
		Produto produto = produtosRepository.getById(id);
		
		produto.setNome(this.nome);
		produto.setDescricao(this.descricao);
		produto.setFornecedor(this.fornecedor);
		
		switch (categoriaInt) {
		case 1:
			produto.setCategoria(Categoria.ELETRONICOS);
			break;
		case 2:
			produto.setCategoria(Categoria.ELETRODOMESTICOS);
			break;
		case 3:
			produto.setCategoria(Categoria.MOVEIS);
			break;
		default:
			break;
		}
		
		return produto;
	}
	
	
}
