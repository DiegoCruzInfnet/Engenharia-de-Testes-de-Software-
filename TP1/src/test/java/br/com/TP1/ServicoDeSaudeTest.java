package br.com.TP1;

import br.com.TP1.CalculoIMC.CalculoIMC;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
// Importações estáticas do Mockito
import static org.mockito.Mockito.*;

/**
 * Este teste usa Mockito para isolar a classe ServicoDeSaude
 * de sua dependência externa (o banco de dados IPerfilUsuarioDAO).
 */
@ExtendWith(MockitoExtension.class)
class ServicoDeSaudeTest {

    // Cria um "Mock" (objeto falso) do banco de dados
    @Mock
    private IPerfilUsuarioDAO daoMock;

    // Cria uma instância real de ServicoDeSaude e injeta o 'daoMock' nela
    @InjectMocks
    private ServicoDeSaude servico;

    @Test
    void testCalcularESalvarIMC() {
        // --- 1. Arrange (Arrumar) ---
        int userId = 123;
        double peso = 70.0;
        double altura = 1.75;

        // !! CORREÇÃO AQUI !!
        // Calcule o valor real EXATO que o método vai usar
        double imcCalculadoReal = CalculoIMC.calcularPeso(peso, altura); // 22.857142857...

        // Comportamento do Mock:
        // Instrua o mock a esperar EXATAMENTE o valor real
        when(daoMock.salvarIMC(eq(userId), eq(imcCalculadoReal))).thenReturn(true);

        // --- 2. Act (Agir) ---
        boolean resultado = servico.calcularESalvarIMC(userId, peso, altura);

        // --- 3. Assert (Verificar) ---

        // Verificação 1: O serviço retornou 'true'?
        assertThat(resultado).isTrue();

        // Verificação 2:
        // Verifique se o mock foi chamado com EXATAMENTE os valores reais
        verify(daoMock, times(1)).salvarIMC(eq(userId), eq(imcCalculadoReal));
    }
}
