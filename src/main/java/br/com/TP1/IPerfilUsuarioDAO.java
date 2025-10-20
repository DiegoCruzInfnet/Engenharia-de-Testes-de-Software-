package br.com.TP1;

// Interface (Contrato) para o Banco de Dados
public interface IPerfilUsuarioDAO {
    // Retorna true se salvou com sucesso
    boolean salvarIMC(int userId, double imc);
}
