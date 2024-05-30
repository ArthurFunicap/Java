package Banco;

public class ContaPoupanca extends Conta{
    private double taxaRendimento = 0.05;

    public ContaPoupanca(String numeroAgencia, String numeroConta, double saldo, Cliente cliente) {
        super(numeroAgencia, numeroConta, saldo, cliente);
    }

    public void simularOperacoes(int meses){

        for(int i = 0; i < meses; i++){
            setSaldo( getSaldo() + ( taxaRendimento * getSaldo() ) );
        }
    }
}
