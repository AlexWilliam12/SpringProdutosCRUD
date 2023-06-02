package com.projetoSMD.produtosCRUD.controllers;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoSMD.produtosCRUD.entity.Produto;
import com.projetoSMD.produtosCRUD.repositories.ProdutoRepository;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("produtos/todos")
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@GetMapping("produtos/{produto}")
	public Object procurar(
			@PathVariable("produto") String produto,
			@RequestParam("id") String id) 
	{
		SortedMap<String, String> response = new TreeMap<>();
		for (int i = 0; i < id.length(); i++) {
			if (!Character.isDigit(id.charAt(i))) {
				response.put("error", "true");
				response.put("message", "Apenas números são permitidos!");
				return response;
			}
		}
		response.put("error:", "true");
		response.put("message:", "Produto não encontrado");
		Optional<Produto> produtoOptional = produtoRepository.findById(Integer.parseInt(id));
		return produtoOptional.isPresent() ? produtoOptional.get() : response;
	}
	
	@PostMapping("produtos/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	public Object adicionar(@RequestBody Produto produto) {
		SortedMap<String, String> response = new TreeMap<>();
		if (produto.getNome() == null || produto.getPreco() == null) {
			response.put("error", "true");
			response.put("message", "Os parâmetros nome e preço são obrigatórios");
			return response;
		}
		else if (produto.getId() != null) {
			response.put("error", "true");
			response.put("message", "Não é necessário enviar o ID");
			return response;
		}
		else
			return produtoRepository.save(produto);
	}

	@PutMapping("produtos/atualizar")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public SortedMap<String, String> atualizar(@RequestBody Produto produto) {
		SortedMap<String, String> response = new TreeMap<>();
		if (produto.getId() == null || produto.getNome() == null || produto.getPreco() == null) {
			response.put("error", "true");
			response.put("message", "Os parâmetros id, nome e preço são obrigatórios");
		}
		else {
			Optional<Produto> produtoOptional = produtoRepository.findById(produto.getId());
			if (produtoOptional.isPresent()) {
				produtoRepository.save(produto);
				response.put("status:", "true");
				response.put("message", "Produto atualizado com sucesso");
			}
			else {
				response.put("error", "true");
				response.put("message:", "Produto não existente");
			}
		}
		return response;
	}

	@DeleteMapping("produtos/excluir")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public SortedMap<String, String> excluir(@RequestBody Produto produto) {
		Optional<Produto> produtoOptional = produtoRepository.findById(produto.getId());
		SortedMap<String, String> response = new TreeMap<>();
		if (produtoOptional.isPresent()) {
			produtoRepository.deleteById(produto.getId());
			response.put("status:", "true");
			response.put("message", "Produto excluido com sucesso");
		}
		else {
			response.put("status", "false");
			response.put("message:", "Produto não existente");
		}
		return response;
	}
}
