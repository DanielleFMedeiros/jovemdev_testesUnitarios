package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import repository.BancoDados;
import enums.EnumSexo;
import models.Pessoa;

public class BancoDadosTest {
	private BancoDados bd = new BancoDados();

	@BeforeEach
	void init() {
		bd.clearData();
		Pessoa p1 = new Pessoa("Pessoa 1", 10, EnumSexo.MASCULINO);
		Pessoa p2 = new Pessoa("Pessoa 2", 15, EnumSexo.FEMININO);
		Pessoa p3 = new Pessoa("Pessoa 3", 20, EnumSexo.MASCULINO);
		Pessoa p4 = new Pessoa("Pessoa 4", 25, EnumSexo.FEMININO);
		bd.add(p1);
		bd.add(p2);
		bd.add(p3);
		bd.add(p4);

	}

	@Test
	@DisplayName("Teste listar todos")
	void testListAll() {
		List<Pessoa> cads = bd.listAll();
		assertEquals(4, cads.size());
		assertEquals("Pessoa 1", cads.get(0).getNome());
		bd.add(new Pessoa("Pessoa 5", 30, EnumSexo.MASCULINO));
		cads = bd.listAll();
		assertEquals(5, cads.size());
		assertEquals(5, cads.get(4).getId());

	}

	@Test
	@DisplayName("Teste Encontrar por Id")
	void testFindById() {
		BancoDados bancoDados = new BancoDados();

		Pessoa pessoa1 = new Pessoa(1);
		pessoa1.setId(1);
		bancoDados.add(pessoa1);

		Pessoa pessoa2 = new Pessoa(2);
		pessoa2.setId(2);
		bancoDados.add(pessoa2);

		Integer idExistente = 2;
		Integer idInexistente = 3;

		Pessoa pessoaEncontradaExistente = bancoDados.findById(idExistente);
		Pessoa pessoaEncontradaInexistente = bancoDados.findById(idInexistente);

		assertEquals(pessoa2, pessoaEncontradaExistente);
		assertNull(pessoaEncontradaInexistente);

	}

	@Test
	@DisplayName("Teste Atualizar por Id")
	void testUpdateById() {
		BancoDados bancoDados = new BancoDados();

		Pessoa pessoa1 = new Pessoa(1);
		bancoDados.add(pessoa1);

		Integer id = 1;
		Pessoa pessoaAtualizada = new Pessoa(1);
		pessoaAtualizada.setNome("Nova Pessoa");

		Pessoa pessoaAtualizadaResult = bancoDados.update(id, pessoaAtualizada);

		assertEquals(pessoaAtualizada, pessoaAtualizadaResult);
		assertEquals("Nova Pessoa", pessoaAtualizadaResult.getNome());

	}

	@Test
	@DisplayName("Teste Não Att por Id")
	void testUpdateByIdNotFound() {
		BancoDados bancoDados = new BancoDados();

		Pessoa pessoa1 = new Pessoa(1);
		bancoDados.add(pessoa1);

		Integer idExistente = 1;
		Integer idInexistente = 2;
		Pessoa pessoaAtualizada = new Pessoa(2);
		pessoaAtualizada.setNome("Nova Pessoa");

		Pessoa pessoaAtualizadaResultExistente = bancoDados.update(idExistente, pessoaAtualizada);
		Pessoa pessoaAtualizadaResultInxistente = bancoDados.update(idInexistente, pessoaAtualizada);

		assertEquals(pessoaAtualizada, pessoaAtualizadaResultExistente);
		assertNull(pessoaAtualizadaResultInxistente);
	}

	@Test
	@DisplayName("Teste Excluir por Id")
	void testDeleteById() {
		BancoDados bancoDados = new BancoDados();

		Pessoa pessoa1 = new Pessoa(1);
		Pessoa pessoa2 = new Pessoa(2);

		bancoDados.add(pessoa1);
		bancoDados.add(pessoa2);

		Integer idExistente = 1;
		Integer idInexistente = 3;

		bancoDados.delete(idExistente);
		bancoDados.delete(idInexistente);

		assertNull(bancoDados.findById(idExistente));
		assertNull(bancoDados.findById(idInexistente));
	}

	@Test
	@DisplayName("Teste Encontrar por Idade (Intervalo sem pessoas)")
	void testFindByIdadeBetween_NoPersonsFound() {
		Integer idadeInicial = 40;
		Integer idadeFinal = 50;

		List<Pessoa> pessoasEncontradas = bd.findByIdadeBetween(idadeInicial, idadeFinal);

		assertEquals(0, pessoasEncontradas.size());
	}

	@Test
	@DisplayName("Teste Encontrar por Sexo")
	void testFindBySexo() {
		EnumSexo sexo = EnumSexo.FEMININO;

		List<Pessoa> pessoasEncontradas = bd.findBySexo(sexo);

		assertEquals(2, pessoasEncontradas.size());
		assertEquals("Pessoa 2", pessoasEncontradas.get(0).getNome());
		assertEquals("Pessoa 4", pessoasEncontradas.get(1).getNome());
	}

	@Test
	public void testClearData() {
	    Assertions.assertEquals(5, bd.listAll().size());

	    bd.clearData();

	    Assertions.assertEquals(0, bd.listAll().size());
	}


}
