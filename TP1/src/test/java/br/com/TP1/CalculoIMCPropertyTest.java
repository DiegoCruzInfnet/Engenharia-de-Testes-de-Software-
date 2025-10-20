package br.com.TP1;

import br.com.TP1.CalculoIMC.CalculoIMC;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;

// Usando o AssertJ, como sugerido no exemplo do trabalho
import static org.assertj.core.api.Assertions.assertThat;

// Usando JUnit 5 para os testes de exceção
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculoIMCPropertyTest {

    // --- Resposta para a Tarefa 2 ---

    /**
     * Testa a propriedade: Para qualquer entrada positiva, o IMC deve ser positivo.
     * Jqwik tentará centenas de valores > 0 para peso e altura.
     */
    @Property
    void imcNuncaDeveSerNegativo(
            @ForAll @Positive double peso,
            @ForAll @Positive double altura
    ) {
        // Usamos nosso método já validado da Parte 1
        double imc = CalculoIMC.calcularPeso(peso, altura);

        // A propriedade: o IMC calculado deve ser maior ou igual a 0.
        assertThat(imc).isGreaterThanOrEqualTo(0);
    }

    // --- Resposta para a Tarefa 3 ---

    /**
     * Testa a propriedade com geradores de dados personalizados (extremos).
     * @ForAll("alturasExtremas") -> Usa o método @Provide com esse nome
     * @ForAll("pesosExtremos")  -> Usa o método @Provide com esse nome
     */
    @Property
    void testaComportamentoComValoresExtremos(
            @ForAll("pesosExtremos") double peso,
            @ForAll("alturasExtremas") double altura
    ) {
        // A propriedade que testamos é a mesma:
        // Ou o cálculo funciona e dá positivo, ou lança uma exceção.

        // Este teste verifica se o nosso método da Parte 1 lida
        // corretamente com entradas que seriam válidas, mas extremas.

        double imc = CalculoIMC.calcularPeso(peso, altura);
        assertThat(imc).isPositive();
    }

    /**
     * Gerador de dados para pesos extremos, mas válidos.
     * Gera valores entre 1kg e 500kg.
     */
    @Provide
    Arbitrary<Double> pesosExtremos() {
        // Gera 'doubles' no intervalo [1.0, 500.0]
        return Arbitraries.doubles().between(1.0, 500.0);
    }

    /**
     * Gerador de dados para alturas extremas, mas válidas.
     * Gera valores entre 0.5m (anão) e 2.8m (gigante).
     */
    @Provide
    Arbitrary<Double> alturasExtremas() {
        // Gera 'doubles' no intervalo [0.5, 2.8]
        return Arbitraries.doubles().between(0.5, 2.8);
    }

    // --- Resposta para a Tarefa 4 ---

    /**
     * Teste "ingênuo" sem restrições de entrada.
     * ESTE TESTE FOI FEITO PARA FALHAR.
     * Ele não usa @Positive, então o Jqwik enviará 0 e valores negativos.
     */
    @Property
    void testIMCComValoresAleatorios(
            @ForAll double peso,
            @ForAll double altura
    ) {
        // A propriedade que testamos: o IMC é sempre positivo.
        // Isso está errado, pois não considera entradas inválidas.

        double imc = CalculoIMC.calcularPeso(peso, altura);
        assertThat(imc).isGreaterThanOrEqualTo(0);
    }

    // --- Resposta para a Tarefa 6 ---

    /**
     * @Example é um teste tradicional (baseado em exemplo),
     * mas que pode ser escrito dentro de um arquivo Jqwik.
     * É usado para 'testes de sanidade' com valores conhecidos.
     */
    @Example
    void testaCasoConhecidoSaudavel() {
        // 1. Arrange (Valores conhecidos)
        double peso = 70.0;
        double altura = 1.75;

        // 2. Act
        double imc = CalculoIMC.calcularPeso(peso, altura);
        String classificacao = CalculoIMC.classificarIMC(imc);

        // 3. Assert
        assertThat(classificacao).isEqualTo("Saudável");
    }

    /**
     * Explicação:
     * Este valor (70kg, 1.75m) foi escolhido por ser um 'exemplo canônico'
     * de um resultado "Saudável" (IMC ~22.9).
     * Enquanto os testes @Property buscam o inesperado, os testes @Example
     * garantem que o comportamento esperado e conhecido não seja
     * quebrado acidentalmente (teste de regressão).
     */
}
