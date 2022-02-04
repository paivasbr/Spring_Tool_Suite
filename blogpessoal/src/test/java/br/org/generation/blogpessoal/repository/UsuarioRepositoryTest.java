package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {

		usuarioRepository.save(new Usuario(0L, "Ste Maria da Silva", "stezinha@email.com.br", "12465278", "  "));
		
		usuarioRepository.save(new Usuario(0L, "Bruninha Vida Silva", "bruninhavidal0ka@email.com.br", "13465278", "  "));
		
		usuarioRepository.save(new Usuario(0L, "Daniela Fulana da Silva", "danifullsilvs@email.com.br", "13465278", "	"));
	
		usuarioRepository.save(new Usuario(0L, "Sandy Sem o Junior", "sandocaalenda@email.com", "13465278", "	"));
	
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("stezinha@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("stezinha@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Ste Maria da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Bruninha Vida Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Daniela Fulana da Silva"));
		
	}
	
}	