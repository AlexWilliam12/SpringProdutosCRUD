package com.projetoSMD.produtosCRUD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoSMD.produtosCRUD.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
