package Banco;

public class ContaCorrente extends Conta{
    private double taxaManutencao = 50.0;

    public ContaCorrente(String numeroAgencia, String numeroConta, double saldo, Cliente cliente) {
        super(numeroAgencia, numeroConta, saldo, cliente);
    }

    public void simularOperacoes(int meses){
        for(int i = 0; i < meses; i++){
            taxaManutencao += taxaManutencao;
        }
        System.out.println("Taxa de Manutenção");
        System.out.println("Simulação do saldo: " + (getSaldo()-taxaManutencao));
    }
}
