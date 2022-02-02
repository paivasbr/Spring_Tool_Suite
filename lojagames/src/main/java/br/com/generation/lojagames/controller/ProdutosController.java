package br.com.generation.lojagames.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.generation.lojagames.model.Produtos;
import br.com.generation.lojagames.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins="*",allowedHeaders="*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public ResponseEntity <List<Produtos>> getAll(){
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> getById(@PathVariable Long id){
		return produtosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<Produtos>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> postProdutos(@Valid @RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> putProdutos(@Valid @RequestBody Produtos produtos){
			return produtosRepository.findById(produtos.getId())
					.map(resposta -> {
							return ResponseEntity.ok().body(produtosRepository.save(produtos));
					}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
		public ResponseEntity<?>deleteProdutos(@PathVariable Long id){
		return produtosRepository.findById(id)
				.map(resposta ->{
					produtosRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produtos>> getPrecoMaiorQue(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtosRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produtos>> getPrecoMenorQue(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtosRepository.findByPrecoLessThanOrderByPreco(preco));
	}
}
