package br.com.victor.stok.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.victor.stok.dto.ProdutoDto;
import br.com.victor.stok.dto.ProdutoForm;
import br.com.victor.stok.model.Produto;
import br.com.victor.stok.repository.ProdutosRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(allowedHeaders = "*")
public class EstoqueController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public List<ProdutoDto> listar() {
		Sort sort = Sort.by("id").descending();
		
		List<Produto> produtos = produtosRepository.findAll(sort);
		return ProdutoDto.converter(produtos);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = form.converter();
		produtosRepository.save(produto);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody ProdutoForm form) {
		Produto produto = form.atualizar(id, produtosRepository);
		
		return ResponseEntity.ok(new ProdutoDto(produto));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		produtosRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
