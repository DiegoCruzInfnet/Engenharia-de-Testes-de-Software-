package br.com.TP1;

import br.com.TP1.CalculoIMC.CalculoIMC;
import org.junit.jupiter.api.Test;

// Importe os métodos estáticos do JUnit 5
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculoIMCTest {

    // --- Testes para o método calcularPeso ---

    @Test
    public void testaCalculoPesoCorreto() {
        // Cenário: 70kg, 1.75m -> IMC = 22.857...
        double imc = CalculoIMC.calcularPeso(70, 1.75);
        // Usamos um 'delta' (0.001) para comparar números 'double'
        assertEquals(22.857, imc, 0.001);
    }

    @Test
    public void testaAlturaZeroLancaExcecao() {
        // Verifica se a exceção que definimos é lançada
        assertThrows(IllegalArgumentException.class, () -> {
            CalculoIMC.calcularPeso(70, 0);
        });
    }

    @Test
    public void testaPesoZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> {
            CalculoIMC.calcularPeso(0, 1.75);
        });
    }

    // --- Testes para o método classificarIMC (Cobertura de 8 branches) ---

    @Test
    public void testaClassificacaoMagrezaGrave() {
        String classificacao = CalculoIMC.classificarIMC(15.9);
        assertEquals("Magreza grave", classificacao);
    }

    @Test
    public void testaClassificacaoMagrezaModerada() {
        String classificacao = CalculoIMC.classificarIMC(16.0);
        assertEquals("Magreza moderada", classificacao);

        classificacao = CalculoIMC.classificarIMC(16.99);
        assertEquals("Magreza moderada", classificacao);
    }

    @Test
    public void testaClassificacaoMagrezaLeve() {
        String classificacao = CalculoIMC.classificarIMC(17.0);
        assertEquals("Magreza leve", classificacao);

        classificacao = CalculoIMC.classificarIMC(18.49);
        assertEquals("Magreza leve", classificacao);
    }

    @Test
    public void testaClassificacaoSaudavel() {
        String classificacao = CalculoIMC.classificarIMC(18.5);
        assertEquals("Saudável", classificacao);

        classificacao = CalculoIMC.classificarIMC(24.99);
        assertEquals("Saudável", classificacao);
    }

    @Test
    public void testaClassificacaoSobrepeso() {
        String classificacao = CalculoIMC.classificarIMC(25.0);
        assertEquals("Sobrepeso", classificacao);

        classificacao = CalculoIMC.classificarIMC(29.99);
        assertEquals("Sobrepeso", classificacao);
    }

    @Test
    public void testaClassificacaoObesidadeGrauI() {
        String classificacao = CalculoIMC.classificarIMC(30.0);
        assertEquals("Obesidade Grau I", classificacao);

        classificacao = CalculoIMC.classificarIMC(34.99);
        assertEquals("Obesidade Grau I", classificacao);
    }

    @Test
    public void testaClassificacaoObesidadeGrauII() {
        String classificacao = CalculoIMC.classificarIMC(35.0);
        assertEquals("Obesidade Grau II", classificacao);

        classificacao = CalculoIMC.classificarIMC(39.99);
        assertEquals("Obesidade Grau II", classificacao);
    }

    @Test
    public void testaClassificacaoObesidadeGrauIII() {
        String classificacao = CalculoIMC.classificarIMC(40.0);
        assertEquals("Obesidade Grau III", classificacao);

        classificacao = CalculoIMC.classificarIMC(46.3);
        assertEquals("Obesidade Grau III", classificacao);
    }
}