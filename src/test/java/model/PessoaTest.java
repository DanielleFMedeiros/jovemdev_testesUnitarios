package model;

import enums.EnumSexo;
import models.Pessoa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PessoaTest {

    private Pessoa pessoa;

    @BeforeEach
    public void setUp() {
        pessoa = new Pessoa(1);
        pessoa.setNome("João");
        pessoa.setIdade(30);
        pessoa.setSexo(EnumSexo.MASCULINO);
    }
    

    @Test
    public void testGetters() {
        Assertions.assertEquals(1, pessoa.getId());
        Assertions.assertEquals("João", pessoa.getNome());
        Assertions.assertEquals(30, pessoa.getIdade());
        Assertions.assertEquals(EnumSexo.MASCULINO, pessoa.getSexo());
    }

    @Test
    public void testConstructorWithId() {
        Pessoa pessoaComId = new Pessoa(2);
        Assertions.assertEquals(2, pessoaComId.getId());
        Assertions.assertNull(pessoaComId.getNome());
        Assertions.assertNull(pessoaComId.getIdade());
        Assertions.assertNull(pessoaComId.getSexo());
    }
    

    @Test
    public void testSetter() {
        String nome = "João";
        int idade = 30;
        EnumSexo sexo = EnumSexo.MASCULINO;

        Pessoa pessoa = new Pessoa(nome, idade, sexo);

        Assertions.assertEquals(nome, pessoa.getNome());
        Assertions.assertEquals(idade, pessoa.getIdade());
        Assertions.assertEquals(sexo, pessoa.getSexo());
    }

    @Test
    public void testRequiredArgsConstructor() {
        String nome = "Maria";
        int idade = 25;
        EnumSexo sexo = EnumSexo.FEMININO;

        Pessoa pessoa = new Pessoa(nome, idade, sexo);

        Assertions.assertEquals(nome, pessoa.getNome());
        Assertions.assertEquals(idade, pessoa.getIdade());
        Assertions.assertEquals(sexo, pessoa.getSexo());
    }
    

    @Test
    public void testSetterNullCheck() {
        Pessoa pessoa = new Pessoa(1);
        EnumSexo sexo = EnumSexo.MASCULINO;

        pessoa.setNome("João");
        pessoa.setIdade(30);
        pessoa.setSexo(sexo);

        Assertions.assertThrows(IllegalArgumentException.class, () -> pessoa.setNome(null));
    }
}
