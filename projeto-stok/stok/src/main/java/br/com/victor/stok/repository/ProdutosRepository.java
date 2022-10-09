package br.com.victor.stok.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.stok.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long>{

}
