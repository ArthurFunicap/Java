package Banco;

public class ContaPoupanca extends Conta{
    private double taxaRendimento = 0.05;

    public ContaPoupanca(String numeroAgencia, String numeroConta, double saldo, Cliente cliente) {
        super(numeroAgencia, numeroConta, saldo, cliente);
    }

    public void simularOperacoes(int meses){
        double rendimento = getSaldo();
        for(int i = 0; i < meses; i++){
            rendimento += (taxaRendimento * rendimento);
        }
        System.out.println("Taxa de Rendimento");
        System.out.println("Simulação do saldo: " + rendimento);
    }
}
