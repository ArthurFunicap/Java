package Banco;

public class ContaCorrente extends Conta{
    private double taxaManutencao = 50.0;

    public ContaCorrente(String numeroAgencia, String numeroConta, double saldo, Cliente cliente) {
        super(numeroAgencia, numeroConta, saldo, cliente);
    }

    public void simularOperacoes(int meses){
        double taxa;

        if(meses == 0){
            System.out.println("Taxa de Manutenção");
            System.out.printf("Simulação do saldo: %.2f\n", getSaldo());
        }
        else{
            taxa = taxaManutencao * meses;
            System.out.println("Taxa de Manutenção");
            System.out.printf("Simulação do saldo: %.2f\n", (getSaldo()-taxa));
        }
    }
}
