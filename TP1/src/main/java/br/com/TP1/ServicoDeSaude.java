package br.com.TP1;

import br.com.TP1.CalculoIMC.CalculoIMC;

// Classe de Serviço que orquestra a lógica
public class ServicoDeSaude {

    // Dependência externa (Ex: Banco de Dados)
    private IPerfilUsuarioDAO perfilDAO;

    // Injeção de Dependência pelo construtor
    public ServicoDeSaude(IPerfilUsuarioDAO perfilDAO) {
        this.perfilDAO = perfilDAO;
    }

    /**
     * Orquestra a lógica de negócio:
     * 1. Calcula o IMC (usando nossa classe estática)
     * 2. Salva o resultado no banco (usando a dependência)
     */
    public boolean calcularESalvarIMC(int userId, double peso, double altura) {
        // 1. Reutiliza nossa lógica de cálculo testada
        double imc = CalculoIMC.calcularPeso(peso, altura);

        // 2. Chama a dependência externa (o banco de dados)
        return perfilDAO.salvarIMC(userId, imc);
    }
}
